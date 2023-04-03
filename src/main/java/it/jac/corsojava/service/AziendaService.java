package it.jac.corsojava.service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.dao.AziendaDao;
import it.jac.corsojava.entity.Azienda;
import it.jac.corsojava.exception.EntityNotFoundException;

public class AziendaService {

	private static Logger log = LogManager.getLogger(AziendaService.class);

	private static AziendaService instance = new AziendaService();

	private AziendaDao dao = AziendaDao.getInstance();

	private AziendaService() {

	}

	public static AziendaService getInstance() {

		return instance;
	}

	public Azienda findById(long idAzienda) {
		
		log.debug("Ricerca Azienda [id={}]", idAzienda);
		
		Azienda result = this.dao.findById(idAzienda);
		
		log.debug("Restituisco {}", result);
		
		return result;
	}
	
	public Azienda create(String email, String partitaIva, String settore, String ragioneSociale) {

		log.debug("Creazione nuova Azienda");
		log.trace("email [{}], partitaIva [{}], settore [{}], ragioneSociale [{}]",
			email, partitaIva, settore, ragioneSociale);
		
//		verifico che i campi obbligatori siano stati valorizzati
//		mi limito a lanciare eccezione se questi non sono validi
		Objects.requireNonNull(email);
		Objects.requireNonNull(partitaIva);
		Objects.requireNonNull(settore);
		Objects.requireNonNull(ragioneSociale);
		
		Azienda entity = new Azienda();
		entity.setEmail(email);
		entity.setPartitaIva(partitaIva);
		entity.setSettore(settore);
		entity.setRagionesociale(ragioneSociale);
		entity.setUtenteIns("admin");
		entity.setDataIns(ZonedDateTime.now());

		this.dao.create(entity);

		log.info("Nuova Azienda [id={}]", entity.getIdAzienda());
		
		return entity;
	}

	public Azienda update(long idAzienda, String email, String partitaIva, String settore, String ragioneSociale) {

		log.debug("Modifica User");
		log.trace("idAzienda [{}], email [{}], partitaIva [{}],  settore [{}],  ragioneSociale [{}]",
			idAzienda, email, partitaIva, settore, ragioneSociale);
		
		Azienda entity = this.dao.findById(idAzienda);
		if (entity == null) {
			throw new EntityNotFoundException("Unable to find azienda [" + idAzienda + "]");
		}

		entity.setEmail(email);
		entity.setPartitaIva(partitaIva);
		entity.setSettore(settore);
		entity.setRagionesociale(ragioneSociale);
		entity.setUtenteMod("admin");
		entity.setDataMod(ZonedDateTime.now());

		this.dao.update(idAzienda, entity);
		
		log.info("Azienda modificata [id={}]", idAzienda);
		
		return entity;
	}
	
	public void delete(long idAzienda) {

		log.debug("Cancellazione User");
		log.trace("id [{}]", idAzienda);

		Azienda entity = this.dao.findById(idAzienda);
		if (entity == null) {
			throw new EntityNotFoundException("Unable to find azienda [" + idAzienda + "]");
		}

		this.dao.delete(idAzienda);
		
		log.info("Azienda eliminato [id={}]", idAzienda);
	}

	public List<Azienda> findAll() {

		List<Azienda> result = new ArrayList<>();
		
		log.debug("Estrazione lista completa Aziende");
		
		result.addAll(this.dao.findAll());
		
		log.debug("Estratti {} elementi", result.size());
		
		return result;
	}
}
