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

import it.jac.corsojava.entity.Azienda;
import it.jac.corsojava.exception.DaoException;

public class AziendaDao {

	private static Logger log = LogManager.getLogger(AziendaDao.class);
	
	private static AziendaDao instance = new AziendaDao();
	
	private AziendaDao() {

//		caricamento dei driver jdbc
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// do nothing
		}
	}
	
	public static AziendaDao getInstance() {
		return instance;
	}
	
	private Connection getConnection() throws SQLException {

		log.info("open connection");
		String jdbcUrl = "jdbc:mysql://localhost:3306/lavoro_gruppo?serverTimezone=Europe/Rome";
		String username = "root";
		String password = "fede";
		
		return DriverManager.getConnection(jdbcUrl, username, password);
	}
	
	public void create(Azienda entity) {

		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO azienda");
		sb.append(" (email, partitaiva, settore, ragionesociale, utenteIns, dataIns)");
		sb.append(" VALUES");
		sb.append(" (?, ?, ?, ?, ?, ?)");
		
		log.debug("SQL [{", sb,"}]");
		log.debug("Entity [{", entity,"}]");
		
		try(Connection conn = getConnection()) {
			
			PreparedStatement pstm = conn.prepareStatement(sb.toString());
			
			int i = 1;
			pstm.setString(i++, entity.getEmail());
			pstm.setString(i++, entity.getPartitaIva());
			pstm.setString(i++, entity.getSettore());
			pstm.setString(i++, entity.getRagionesociale());
			pstm.setString(i++, entity.getUtenteIns());
			pstm.setTimestamp(i++, Timestamp.valueOf(entity.getDataIns().toLocalDateTime()));
			
			log.debug("Tento di eseguire inserimento dati");
			
			int result = pstm.executeUpdate();
			
			log.debug("Modificate {} righe", result);
			
		} catch(SQLException e) {
			
			throw new DaoException("Error creating Azienda", e);
		}
	}
	
	public void update(long id, Azienda entity) {

		StringBuilder sb = new StringBuilder();
		sb.append(" update azienda");
		sb.append(" SET ");
		sb.append(" email = ?,");
		sb.append(" partitaiva = ?,");
		sb.append(" settore = ?,");
		sb.append(" ragionesociale = ?,");
		sb.append(" utente_mod = ?,");
		sb.append(" data_mod = ?");
		sb.append(" where idazienda = ?");
		
		log.debug("SQL [{}]", sb);
		log.debug("Entity [{}]", entity);
		
		try(Connection conn = getConnection()) {
			
			PreparedStatement pstm = conn.prepareStatement(sb.toString());
			
			int i = 1;
			pstm.setString(i++, entity.getEmail());
			pstm.setString(i++, entity.getPartitaIva());
			pstm.setString(i++, entity.getSettore());
			pstm.setString(i++, entity.getRagionesociale());
			pstm.setString(i++, entity.getUtenteMod());
			
//			si può anche usare la funzione setObject(...)
			pstm.setObject(i++, entity.getDataMod());
			
			pstm.setLong(i, id);
			log.debug("Tento di eseguire modifica dati");
			
			int result = pstm.executeUpdate();
			
			log.debug("Modificate {} righe", result);
			
		} catch(SQLException e) {
			
			throw new DaoException("Error updating Azienda", e);
		}
	}

	public void delete(Azienda entity) {
		
		delete(entity.getIdAzienda());
	}
	
	public void delete(long id) {

		StringBuilder sb = new StringBuilder();
		sb.append(" delete from azienda");
		sb.append(" where idazienda = ?");
		
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
			
			throw new DaoException("Error deleting Azienda", e);
		}
	}
	
	public Azienda findById(long idAzienda) {

		Azienda result = null;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT idazienda, email, partitaiva, settore, ragionesociale, utenteIns, utenteMod, dataIns, dataMod ");
		sb.append(" FROM azienda");
		sb.append(" WHERE idazienda = ?");
		
		try(Connection conn = getConnection()) {
			
			PreparedStatement pstm = conn.prepareStatement(sb.toString());
			
			int i = 1;
			pstm.setLong(i++, idAzienda);
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				result = new Azienda();
				
				result.setIdAzienda(idAzienda);
				result.setEmail(rs.getString("email"));
				result.setPartitaIva(rs.getString("partitaiva"));
				result.setSettore(rs.getString("settore"));
				result.setRagionesociale(rs.getString("ragionesociale"));
				result.setUtenteIns(rs.getString("utenteIns"));
				result.setUtenteMod(rs.getString("utenteMod"));
				result.setDataIns(ZonedDateTime.of(rs.getTimestamp("dataIns").toLocalDateTime(), ZoneId.systemDefault()));
				Timestamp dataMod = rs.getTimestamp("dataMod");
				if (dataMod != null) {
					result.setDataMod(ZonedDateTime.of(dataMod.toLocalDateTime(), ZoneId.systemDefault()));
				}
			}
			
		} catch(SQLException e) {
			throw new DaoException("Error loading Azienda", e);
		}
		
		return result;
	}

	public List<Azienda> findAll() {
		
		List<Azienda> resultList = new ArrayList<>();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT idazienda, email, partitaiva, settore, ragionesociale, utenteIns, utenteMod, dataIns, dataMod ");
		sb.append(" FROM azienda");
		
		try(Connection conn = getConnection()) {
			
			PreparedStatement pstm = conn.prepareStatement(sb.toString());
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				Azienda azienda = new Azienda();
				
				azienda.setIdAzienda(rs.getLong("idazienda"));
				azienda.setEmail(rs.getString("email"));
				azienda.setPartitaIva(rs.getString("partitaiva"));
				azienda.setSettore(rs.getString("settore"));
				azienda.setRagionesociale(rs.getString("ragionesociale"));
				azienda.setUtenteIns(rs.getString("utenteIns"));
				azienda.setUtenteMod(rs.getString("utenteMod"));
				azienda.setDataIns(ZonedDateTime.of(rs.getTimestamp("dataIns").toLocalDateTime(), ZoneId.systemDefault()));
				Timestamp dataMod = rs.getTimestamp("dataMod");
				if (dataMod != null) {
					azienda.setDataMod(ZonedDateTime.of(dataMod.toLocalDateTime(), ZoneId.systemDefault()));
				}
				
				resultList.add(azienda);
			}
			
		} catch(SQLException e) {
			throw new DaoException("Error loading Azienda", e);
		}
		
		return resultList;
	}
}
