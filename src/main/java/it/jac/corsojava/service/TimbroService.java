package it.jac.corsojava.service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.dao.TimbroDao;
import it.jac.corsojava.entity.Timbro;
import it.jac.corsojava.exception.EntityNotFoundException;

public class TimbroService {

	private static Logger log = LogManager.getLogger(TimbroService.class);

	private static TimbroService instance = new TimbroService();

	private TimbroDao dao = TimbroDao.getInstance();

	private TimbroService() {

	}

	public static TimbroService getInstance() {

		return instance;
	}

	public Timbro findById(long idTimbro) {
		
		log.debug("Ricerca Timbro [id={}]", idTimbro);
		
		Timbro result = this.dao.findById(idTimbro);
		
		log.debug("Restituisco {}", result);
		
		return result;
	}
	
	public Timbro create(ZonedDateTime dataOra, String tipo, long idAzienda, long idDipendente) {

		log.debug("Creazione nuovo TImbro");
		log.trace("dataOra [{}], tipo [{}]",
				dataOra, tipo);
		
//		verifico che i campi obbligatori siano stati valorizzati
//		mi limito a lanciare eccezione se questi non sono validi
		Objects.requireNonNull(dataOra);
		Objects.requireNonNull(tipo);
		Objects.requireNonNull(idAzienda);
		Objects.requireNonNull(idDipendente);
		
		Timbro entity = new Timbro();
		entity.setDataOra(dataOra);
		entity.setTipo(tipo);
		entity.setIdAzienda(idAzienda);
		entity.setIdDipendente(idDipendente);

		this.dao.create(entity);

		log.info("Nuovo TImbro [id={}]", entity.getIdTimbro());
		
		return entity;
	}

	public void delete(long idTimbro) {

		log.debug("Cancellazione TImbro");
		log.trace("id [{}]", idTimbro);

		Timbro entity = this.dao.findById(idTimbro);
		if (entity == null) {
			throw new EntityNotFoundException("Unable to find TImbro [" + idTimbro + "]");
		}

		this.dao.delete(idTimbro);
		
		log.info("TImbro eliminato [id={}]", idTimbro);
	}

	public List<Timbro> findAll() {

		List<Timbro> result = new ArrayList<>();
		
		log.debug("Estrazione lista completa TImbro");
		
		result.addAll(this.dao.findAll());
		
		log.debug("Estratti {} elementi", result.size());
		
		return result;
	}
}
