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

import it.jac.corsojava.entity.Timbro;
import it.jac.corsojava.exception.DaoException;

public class TimbroDao {
private static Logger log = LogManager.getLogger(TimbroDao.class);
	
	private static TimbroDao instance = new TimbroDao();
	
	private TimbroDao() {

//		caricamento dei driver jdbc
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// do nothing
		}
	}
	
	public static TimbroDao getInstance() {
		return instance;
	}
	
	private Connection getConnection() throws SQLException {

		log.info("open connection");
		String jdbcUrl = "jdbc:mysql://localhost:3306/lavoro_gruppo?serverTimezone=Europe/Rome";
		String username = "root";
		String password = "nrarqp4132.C";
		
		return DriverManager.getConnection(jdbcUrl, username, password);
	}
	
	public void create(Timbro entity) {

		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO timbro");
		sb.append(" (dataora, tipo, idazienda, iddipendente)");
		sb.append(" VALUES");
		sb.append(" (?, ?, ?, ?)");
		
		log.debug("SQL [{", sb,"}]");
		log.debug("Entity [{", entity,"}]");
		
		try(Connection conn = getConnection()) {
			
			PreparedStatement pstm = conn.prepareStatement(sb.toString());
			
			int i = 1;
			pstm.setTimestamp(i++, Timestamp.valueOf(entity.getDataOra()));
			pstm.setString(i++, entity.getTipo());
			pstm.setLong(i++, entity.getIdAzienda());
			pstm.setLong(i++, entity.getIdDipendente());
			
			log.debug("Tento di eseguire inserimento dati");
			
			int result = pstm.executeUpdate();
			
			log.debug("Modificate {} righe", result);
			
		} catch(SQLException e) {
			
			throw new DaoException("Error creating Timbro", e);
		}
	}
	
	public void delete(Timbro entity) {
		
		delete(entity.getIdTimbro());
	}
	
	public void delete(long id) {

		StringBuilder sb = new StringBuilder();
		sb.append(" delete from timbro");
		sb.append(" where idtimbro = ?");
		
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
			
			throw new DaoException("Error deleting Timbro", e);
		}
	}
	
	public Timbro findById(long idTimbro) {

		Timbro result = null;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT *");
		sb.append(" FROM timbro");
		sb.append(" WHERE idtimbro = ?");
		
		try(Connection conn = getConnection()) {
			
			PreparedStatement pstm = conn.prepareStatement(sb.toString());
			
			int i = 1;
			pstm.setLong(i++, idTimbro);
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				result = new Timbro();
				
				result.setIdTimbro(idTimbro);
				result.setIdDipendente(rs.getLong("iddipendente"));
				result.setTipo(rs.getString("tipo"));
				result.setDataOra(rs.getTimestamp("dataora").toLocalDateTime());
				result.setIdAzienda(rs.getLong("idazienda"));
				
			}
			
		} catch(SQLException e) {
			throw new DaoException("Error loading Timbro", e);
		}
		
		return result;
	}

	public List<Timbro> findAll() {
		
		List<Timbro> resultList = new ArrayList<>();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT *");
		sb.append(" FROM timbro");
		
		try(Connection conn = getConnection()) {
			
			PreparedStatement pstm = conn.prepareStatement(sb.toString());
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				Timbro timbro = new Timbro();
				
				timbro.setIdTimbro(rs.getLong("idTimbro"));
				timbro.setIdDipendente(rs.getLong("iddipendente"));
				timbro.setTipo(rs.getString("tipo"));
				timbro.setDataOra(rs.getTimestamp("dataora").toLocalDateTime());
				timbro.setIdAzienda(rs.getLong("idazienda"));
				
				resultList.add(timbro);
			}
			
		} catch(SQLException e) {
			throw new DaoException("Error loading Timbro", e);
		}
		
		return resultList;
	}
}
