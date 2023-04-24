package it.jac.corsojava.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.entity.Dipendente;
import it.jac.corsojava.exception.DaoException;

public class DipendenteDao {
private static Logger log = LogManager.getLogger(DipendenteDao.class);
	
	private static DipendenteDao instance = new DipendenteDao();
	
	private DipendenteDao() {

//		caricamento dei driver jdbc
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// do nothing
		}
	}
	
	public static DipendenteDao getInstance() {
		return instance;
	}
	
	private Connection getConnection() throws SQLException {

		log.info("open connection");
		String jdbcUrl = "jdbc:mysql://localhost:3306/lavoro_gruppo?serverTimezone=Europe/Rome";
		String username = "root";
		String password = "fede";
		
		return DriverManager.getConnection(jdbcUrl, username, password);
	}
	
	public void create(Dipendente entity) {

		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO dipendente");
		sb.append(" (nome, cognome, datanascita, sesso, email, password, idazienda, idbusinessunit, utenteins, datains)");
		sb.append(" VALUES");
		sb.append(" (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		log.debug("SQL [{", sb,"}]");
		log.debug("Entity [{", entity,"}]");
		
		try(Connection conn = getConnection()) {
			
			PreparedStatement pstm = conn.prepareStatement(sb.toString());
			
			int i = 1;
			pstm.setString(i++, entity.getNome());
			pstm.setString(i++, entity.getCognome());
			pstm.setObject(i++, entity.getDataNascita());
			pstm.setString(i++, entity.getSesso());
			pstm.setString(i++, entity.getEmail());
			pstm.setString(i++, entity.getPassword());
			
			pstm.setLong(i++, entity.getIdAzienda());
			pstm.setLong(i++, entity.getIdBusinessUnit());
			
			pstm.setString(i++, entity.getUtenteIns());
			pstm.setTimestamp(i++, Timestamp.valueOf(entity.getDataIns().toLocalDateTime()));
			
			log.debug("Tento di eseguire inserimento dati");
			
			int result = pstm.executeUpdate();
			
			log.debug("Modificate {} righe", result);
			
		} catch(SQLException e) {
			
			throw new DaoException("Error creating Dipendente", e);
		}
	}
	
	public void update(long id, Dipendente entity) {

		StringBuilder sb = new StringBuilder();
		sb.append(" update dipendente");
		sb.append(" SET ");
		sb.append(" nome = ?,");
		sb.append(" cognome = ?,");
		sb.append(" datanascita = ?,");
		sb.append(" sesso = ?,");
		sb.append(" email = ?,");
		sb.append(" password = ?,");
		sb.append(" utentemod = ?,");
		sb.append(" datamod = ?");
		
		sb.append(" where iddipendente = ?");
		
		log.debug("SQL [{}]", sb);
		log.debug("Entity [{}]", entity);
		
		try(Connection conn = getConnection()) {
			
			PreparedStatement pstm = conn.prepareStatement(sb.toString());
			
			int i = 1;
			pstm.setString(i++, entity.getNome());
			pstm.setString(i++, entity.getCognome());
			
			pstm.setDate(i++, Date.valueOf(entity.getDataNascita()));
			
			pstm.setString(i++, entity.getSesso());
			pstm.setString(i++, entity.getEmail());
			pstm.setString(i++, entity.getPassword());
			
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

	public void delete(Dipendente entity) {
		
		delete(entity.getIdDipendente());
	}
	
	public void delete(long id) {

		StringBuilder sb = new StringBuilder();
		sb.append(" delete from dipendente");
		sb.append(" where iddipendente = ?");
		
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
			
			throw new DaoException("Error deleting Dipendente", e);
		}
	}
	
	public Dipendente findById(long idDipendente) {

		Dipendente result = null;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT *");
		sb.append(" FROM dipendente");
		sb.append(" WHERE iddipendente = ?");
		
		try(Connection conn = getConnection()) {
			
			PreparedStatement pstm = conn.prepareStatement(sb.toString());
			
			int i = 1;
			pstm.setLong(i++, idDipendente);
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				result = new Dipendente();
				
				result.setIdDipendente(idDipendente);
				result.setNome(rs.getString("nome"));
				result.setCognome(rs.getString("cognome"));
				result.setDataNascita(rs.getDate("datanascita").toLocalDate());
				result.setSesso(rs.getString("sesso"));
				result.setEmail(rs.getString("email"));
				result.setPassword(rs.getString("password"));
				result.setUtenteIns(rs.getString("utenteIns"));
				result.setUtenteMod(rs.getString("utenteMod"));
				result.setDataIns(ZonedDateTime.of(rs.getTimestamp("dataIns").toLocalDateTime(), ZoneId.systemDefault()));
				Timestamp dataMod = rs.getTimestamp("dataMod");
				if (dataMod != null) {
					result.setDataMod(ZonedDateTime.of(dataMod.toLocalDateTime(), ZoneId.systemDefault()));
				}
			}
			
		} catch(SQLException e) {
			throw new DaoException("Error loading Dipendente", e);
		}
		
		return result;
	}

	public List<Dipendente> findAll() {
		
		List<Dipendente> resultList = new ArrayList<>();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT *");
		sb.append(" FROM dipendente");
		
		try(Connection conn = getConnection()) {
			
			PreparedStatement pstm = conn.prepareStatement(sb.toString());
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				Dipendente dipendente = new Dipendente();
				
				dipendente.setIdBusinessUnit(rs.getLong("idbusinessunit"));
				dipendente.setNome(rs.getString("nome"));
				dipendente.setCognome(rs.getString("cognome"));
				dipendente.setDataNascita(rs.getDate("datanascita").toLocalDate());
				dipendente.setSesso(rs.getString("sesso"));
				dipendente.setEmail(rs.getString("email"));
				dipendente.setPassword(rs.getString("password"));
				dipendente.setUtenteIns(rs.getString("utenteIns"));
				dipendente.setUtenteMod(rs.getString("utenteMod"));
				dipendente.setDataIns(ZonedDateTime.of(rs.getTimestamp("dataIns").toLocalDateTime(), ZoneId.systemDefault()));
				Timestamp dataMod = rs.getTimestamp("dataMod");
				if (dataMod != null) {
					dipendente.setDataMod(ZonedDateTime.of(dataMod.toLocalDateTime(), ZoneId.systemDefault()));
				}
				
				resultList.add(dipendente);
			}
			
		} catch(SQLException e) {
			throw new DaoException("Error loading Dipendente", e);
		}
		
		return resultList;
	}
}
