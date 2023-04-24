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
		
		//Esempi di create
		
		AziendaService.getInstance().create("info@costruzioniiroma.it", "07895640217", "Edile", "Costruzioni Roma S.r.l.");
		AziendaService.getInstance().create("info@lofficinadelgusto.it", "03458960373", "Meccanico", "L'officina del Gusto");
		AziendaService.getInstance().create("info@infolab.it", "06674680984", "Informatico", "InfoLab");
		
		BusinessUnitService.getInstance().create("Vendite",4);
		BusinessUnitService.getInstance().create("Risorse Umane", 5);
		BusinessUnitService.getInstance().create("Finanziaria", 6);
		
		DipendenteService.getInstance().create("Federico", "Bonomi", LocalDate.of(2003, 05, 11), "M", "fede.bonomi@gmail.com", "1234", 4, 7); 
		DipendenteService.getInstance().create("Andrea", "Dolci", LocalDate.of(2003, 12, 11), "M", "dolciandrea@gmail.com", "5678", 6 , 9);
		DipendenteService.getInstance().create("Simone", "Airaghi", LocalDate.of(2003, 06, 03), "M", "simone.airaghi@gmail.com", "9101", 5, 8);
		
		TimbroService.getInstance().create(LocalDateTime.now(), "Entrata", 5, 7);
		TimbroService.getInstance().create(LocalDateTime.now(), "Uscita", 4, 6);
		TimbroService.getInstance().create(LocalDateTime.now(), "Uscita", 6, 8);
		
		//Esempi di Update
		
//		AziendaService.getInstance().update(1, "azienda@gmail.com", "08100750010", "Informatica", "Evergreen di Mario Rossi");
//		BusinessUnitService.getInstance().update(4, "Contabilità");
//		DipendenteService.getInstance().update("Giorgia", "Baracchetti", LocalDate.of(2002, 9, 6), "F", 2);
		
		
		//Esempi delete
		
//		AziendaService.getInstance().delete(1);
//		BusinessUnitService.getInstance().delete(3);
//		DipendenteService.getInstance().delete(1);
//		TimbroService.getInstance().delete(1);
		
		
		
		log.info("Applicazione terminata");
	}
}
