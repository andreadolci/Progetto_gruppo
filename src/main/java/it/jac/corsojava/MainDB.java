package it.jac.corsojava;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.service.AziendaService;
import it.jac.corsojava.service.BusinessUnitService;


public class MainDB {

	private static Logger log = LogManager.getLogger(MainDB.class);
	
	public static void main(String[] args) 
	{
		log.info("Applicazione inizia");
//		AziendaService.getInstance().create("@gmail.com", "partitaIva", "Vendite", "ragioneSociale");
		BusinessUnitService.getInstance().create("Vendite", 1);
		log.info("Applicazione terminata");
	}
}
