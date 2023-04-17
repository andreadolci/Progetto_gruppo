package it.jac.corsojava.service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.dao.BusinessUnitDao;
import it.jac.corsojava.entity.BusinessUnit;
import it.jac.corsojava.exception.EntityNotFoundException;

public class BusinessUnitService {

	private static Logger log = LogManager.getLogger(BusinessUnitService.class);

	private static BusinessUnitService instance = new BusinessUnitService();

	private BusinessUnitDao dao = BusinessUnitDao.getInstance();

	private BusinessUnitService() {

	}

	public static BusinessUnitService getInstance() {

		return instance;
	}

	public BusinessUnit findById(long idBusinessUnit) {
		
		log.debug("Ricerca BusinessUnit [id={}]", idBusinessUnit);
		
		BusinessUnit result = this.dao.findById(idBusinessUnit);
		
		log.debug("Restituisco {}", result);
		
		return result;
	}
	
	public BusinessUnit create(String area, long idAzienda) {

		log.debug("Creazione nuova BusinessUnit");
		log.trace("area [{}]",
			area);
		
//		verifico che i campi obbligatori siano stati valorizzati
//		mi limito a lanciare eccezione se questi non sono validi
		Objects.requireNonNull(area);
		
		BusinessUnit entity = new BusinessUnit();
		entity.setArea(area);
		entity.setIdAzienda(idAzienda);
		entity.setUtenteIns("admin");
		entity.setDataIns(ZonedDateTime.now());

		this.dao.create(entity);

		log.info("Nuova BusinessUnit [id={}]", entity.getIdBusinessUnit());
		
		return entity;
	}

	public BusinessUnit update(long idBusiness_unit, String area) {

		log.debug("Modifica BusinessUnit");
		log.trace("idBusiness_unit [{}], area [{}]",
			idBusiness_unit, area);
		
		BusinessUnit entity = this.dao.findById(idBusiness_unit);
		if (entity == null) {
			throw new EntityNotFoundException("Unable to find BusinessUnit [" + idBusiness_unit + "]");
		}

		entity.setArea(area);
		entity.setUtenteMod("admin");
		entity.setDataMod(ZonedDateTime.now());

		this.dao.update(idBusiness_unit, entity);
		
		log.info("BusinessUnit modificata [id={}]", idBusiness_unit);
		
		return entity;
	}
	
	public void delete(long idBusiness_unit) {

		log.debug("Cancellazione BusinessUnit");
		log.trace("id [{}]", idBusiness_unit);

		BusinessUnit entity = this.dao.findById(idBusiness_unit);
		if (entity == null) {
			throw new EntityNotFoundException("Unable to find businessUnit [" + idBusiness_unit + "]");
		}

		this.dao.delete(idBusiness_unit);
		
		log.info("BusinessUnit eliminato [id={}]", idBusiness_unit);
	}

	public List<BusinessUnit> findAll() {

		List<BusinessUnit> result = new ArrayList<>();
		
		log.debug("Estrazione lista completa BusinessUnit");
		
		result.addAll(this.dao.findAll());
		
		log.debug("Estratti {} elementi", result.size());
		
		return result;
	}
}
