package it.jac.corsojava.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.entity.BusinessUnit;
import it.jac.corsojava.exception.DaoException;

public class BusinessUnitDao {
private static Logger log = LogManager.getLogger(BusinessUnitDao.class);
	
	private static BusinessUnitDao instance = new BusinessUnitDao();
	
	private BusinessUnitDao() {

//		caricamento dei driver jdbc
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// do nothing
		}
	}
	
	public static BusinessUnitDao getInstance() {
		return instance;
	}
	
	private Connection getConnection() throws SQLException {

		log.info("open connection");
		String jdbcUrl = "jdbc:mysql://localhost:3306/lavoro_gruppo?serverTimezone=Europe/Rome";
		String username = "root";
		String password = "nrarqp4132.C";
		
		return DriverManager.getConnection(jdbcUrl, username, password);
	}
	
	public void create(BusinessUnit entity) {

		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO business_unit");
		sb.append(" (area, utenteIns, dataIns, idazienda)");
		sb.append(" VALUES");
		sb.append(" (?, ?, ?, ?)");
		
		log.debug("SQL [{", sb,"}]");
		log.debug("Entity [{", entity,"}]");
		
		try(Connection conn = getConnection()) {
			
			PreparedStatement pstm = conn.prepareStatement(sb.toString());
			
			int i = 1;
			pstm.setString(i++, entity.getArea());
			pstm.setString(i++, entity.getUtenteIns());
			pstm.setTimestamp(i++, Timestamp.valueOf(entity.getDataIns().toLocalDateTime()));
			pstm.setLong(i++, entity.getIdAzienda());
			
			log.debug("Tento di eseguire inserimento dati");
			
			int result = pstm.executeUpdate();
			
			log.debug("Modificate {} righe", result);
			
		} catch(SQLException e) {
			
			throw new DaoException("Error creating BusinessUnit", e);
		}
	}
	
	public void update(long id, BusinessUnit entity) {

		StringBuilder sb = new StringBuilder();
		sb.append(" update business_unit");
		sb.append(" SET ");
		sb.append(" area = ?,");
		sb.append(" utente_mod = ?,");
		sb.append(" data_mod = ?");
		sb.append(" where idbusiness_unit = ?");
		
		log.debug("SQL [{}]", sb);
		log.debug("Entity [{}]", entity);
		
		try(Connection conn = getConnection()) {
			
			PreparedStatement pstm = conn.prepareStatement(sb.toString());
			
			int i = 1;
			pstm.setString(i++, entity.getArea());
			pstm.setString(i++, entity.getUtenteMod());
			
//			si può anche usare la funzione setObject(...)
			pstm.setObject(i++, entity.getDataMod());
			
			pstm.setLong(i, id);
			log.debug("Tento di eseguire modifica dati");
			
			int result = pstm.executeUpdate();
			
			log.debug("Modificate {} righe", result);
			
		} catch(SQLException e) {
			
			throw new DaoException("Error updating BusinessUnit", e);
		}
	}

	public void delete(BusinessUnit entity) {
		
		delete(entity.getIdBusinessUnit());
	}
	
	public void delete(long id) {

		StringBuilder sb = new StringBuilder();
		sb.append(" delete from business_unit");
		sb.append(" where idbusiness_unit = ?");
		
		log.debug("SQL [{}]", sb);
		log.debug("Id [{}]", id);
		
		try(Connection conn = getConnection()) {
			
			PreparedStatement pstm = conn.prepareStatement(sb.toString());
			
			int i = 1;
			pstm.setLong(i, id);
			log.debug("Tento di eseguire modifica dati");
			
			int result = pstm.executeUpdate();
			
			log.debug("Modificate {} righe", result);
			
		} catch(SQLException e) {
			
			throw new DaoException("Error deleting BusinessUnit", e);
		}
	}
	
	public BusinessUnit findById(long idBusinessUnit) {

		BusinessUnit result = null;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT *");
		sb.append(" FROM business_unit");
		sb.append(" WHERE idbusiness_unit = ?");
		
		try(Connection conn = getConnection()) {
			
			PreparedStatement pstm = conn.prepareStatement(sb.toString());
			
			int i = 1;
			pstm.setLong(i++, idBusinessUnit);
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				result = new BusinessUnit();
				
				result.setIdBusinessUnit(idBusinessUnit);
				result.setArea(rs.getString("area"));
				result.setUtenteIns(rs.getString("utenteIns"));
				result.setUtenteMod(rs.getString("utenteMod"));
				result.setDataIns(ZonedDateTime.of(rs.getTimestamp("dataIns").toLocalDateTime(), ZoneId.systemDefault()));
				Timestamp dataMod = rs.getTimestamp("dataMod");
				if (dataMod != null) {
					result.setDataMod(ZonedDateTime.of(dataMod.toLocalDateTime(), ZoneId.systemDefault()));
				}
			}
			
		} catch(SQLException e) {
			throw new DaoException("Error loading BusinessUnit", e);
		}
		
		return result;
	}

	public List<BusinessUnit> findAll() {
		
		List<BusinessUnit> resultList = new ArrayList<>();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT *");
		sb.append(" FROM business_unit");
		
		try(Connection conn = getConnection()) {
			
			PreparedStatement pstm = conn.prepareStatement(sb.toString());
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				BusinessUnit businessUnit = new BusinessUnit();
				
				businessUnit.setIdBusinessUnit(rs.getLong("idbusiness_unit"));
				businessUnit.setArea(rs.getString("area"));
				businessUnit.setUtenteIns(rs.getString("utenteIns"));
				businessUnit.setUtenteMod(rs.getString("utenteMod"));
				businessUnit.setDataIns(ZonedDateTime.of(rs.getTimestamp("dataIns").toLocalDateTime(), ZoneId.systemDefault()));
				Timestamp dataMod = rs.getTimestamp("dataMod");
				if (dataMod != null) {
					businessUnit.setDataMod(ZonedDateTime.of(dataMod.toLocalDateTime(), ZoneId.systemDefault()));
				}
				
				resultList.add(businessUnit);
			}
			
		} catch(SQLException e) {
			throw new DaoException("Error loading BusinessUnit", e);
		}
		
		return resultList;
	}
}
