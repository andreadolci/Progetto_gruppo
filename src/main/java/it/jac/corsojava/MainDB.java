package it.jac.corsojava;

import java.time.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.service.AziendaService;
import it.jac.corsojava.service.BusinessUnitService;
import it.jac.corsojava.service.DipendenteService;
import it.jac.corsojava.service.TimbroService;


public class MainDB {

	private static Logger log = LogManager.getLogger(MainDB.class);
	
	public static void main(String[] args) 
	{
		log.info("Applicazione inizia");
//		AziendaService.getInstance().create("@gmail.com", "partitaIva", "Elettronica", "ragioneSociale");
//		AziendaService.getInstance().create("prova2@gmail.com", "partitaIva2", "Elettronica", "ragioneSociale2");
//		BusinessUnitService.getInstance().create("Vendite", 2);
//		BusinessUnitService.getInstance().create("Risorse Umane", 1);
//		BusinessUnitService.getInstance().create("Risorse Umane", 2);
//		BusinessUnitService.getInstance().create("Relazioni Internazionali", 1);
//		BusinessUnitService.getInstance().create("Relazioni Internazionali", 3);
//		DipendenteService.getInstance().create("Federico", "Bonomi", LocalDate.of(2003, 5, 11), "M", 1, 1);
//		DipendenteService.getInstance().create("Andrea", "Dolci", LocalDate.of(2003, 12, 11), "F", 1, 2);
//		DipendenteService.getInstance().create("Simone", "Airaghi", LocalDate.of(2003, 6, 3), "M", 1, 2);
//		TimbroService.getInstance().create(ZonedDateTime.now(), "Entrata", 1, 1);
//		TimbroService.getInstance().create(ZonedDateTime.now(), "Uscita", 1, 1);
		log.info("Applicazione terminata");
	}
}
