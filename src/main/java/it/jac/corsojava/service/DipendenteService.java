package it.jac.corsojava.service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.dao.DipendenteDao;
import it.jac.corsojava.entity.Dipendente;
import it.jac.corsojava.exception.EntityNotFoundException;

public class DipendenteService {

	private static Logger log = LogManager.getLogger(DipendenteService.class);

	private static DipendenteService instance = new DipendenteService();

	private DipendenteDao dao = DipendenteDao.getInstance();

	private DipendenteService() {

	}

	public static DipendenteService getInstance() {

		return instance;
	}

	public Dipendente findById(long idDipendente) {
		
		log.debug("Ricerca Dipendente [id={}]", idDipendente);
		
		Dipendente result = this.dao.findById(idDipendente);
		
		log.debug("Restituisco {}", result);
		
		return result;
	}
	
	public Dipendente create(String nome, String cognome, LocalDate dataNascita, String sesso, long idAzienda, long idBusinessUnit) {

		log.debug("Creazione nuova Dipendente");
		log.trace("nome [{}], cognome [{}], dataNascita [{}], sesso [{}]",
			nome, cognome, dataNascita, sesso);
		
//		verifico che i campi obbligatori siano stati valorizzati
//		mi limito a lanciare eccezione se questi non sono validi
		Objects.requireNonNull(nome);
		Objects.requireNonNull(cognome);
		Objects.requireNonNull(dataNascita);
		Objects.requireNonNull(sesso);
		
		Dipendente entity = new Dipendente();
		entity.setNome(nome);
		entity.setCognome(cognome);
		entity.setDataNascita(dataNascita);
		entity.setSesso(sesso);
		entity.setIdAzienda(idAzienda);
		entity.setIdBusinessUnit(idBusinessUnit);
		
		entity.setUtenteIns("admin");
		entity.setDataIns(ZonedDateTime.now());

		this.dao.create(entity);

		log.info("Nuova Dipendente [id={}]", entity.getIdDipendente());
		
		return entity;
	}

	public Dipendente update(String nome, String cognome, LocalDate dataNascita, String sesso, long idDipendente) {

		log.debug("Modifica Dipendente");
		log.trace("idDipendente [{}], nome [{}], cognome[{}], dataNascita[{}], sesso[{}], ",
			idDipendente, nome, cognome, dataNascita, sesso);
		
		Dipendente entity = this.dao.findById(idDipendente);
		if (entity == null) {
			throw new EntityNotFoundException("Unable to find Dipendente [" + idDipendente + "]");
		}

		entity.setNome(nome);
		entity.setCognome(cognome);;
		entity.setDataIns(ZonedDateTime.now());
		entity.setSesso(sesso);
		
		entity.setUtenteMod("admin");
		entity.setDataMod(ZonedDateTime.now());

		this.dao.update(idDipendente, entity);
		
		log.info("Dipenndente modificata [id={}]", idDipendente);
		
		return entity;
	}
	
	public void delete(long idDipendente) {

		log.debug("Cancellazione Dipendente");
		log.trace("id [{}]", idDipendente);

		Dipendente entity = this.dao.findById(idDipendente);
		if (entity == null) {
			throw new EntityNotFoundException("Unable to find dipendente [" + idDipendente + "]");
		}

		this.dao.delete(idDipendente);
		
		log.info("Dipendente eliminato [id={}]", idDipendente);
	}

	public List<Dipendente> findAll() {

		List<Dipendente> result = new ArrayList<>();
		
		log.debug("Estrazione lista completa Dipendente");
		
		result.addAll(this.dao.findAll());
		
		log.debug("Estratti {} elementi", result.size());
		
		return result;
	}
}
