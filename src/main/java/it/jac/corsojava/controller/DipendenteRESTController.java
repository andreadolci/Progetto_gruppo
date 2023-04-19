package it.jac.corsojava.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.entity.Dipendente;
import it.jac.corsojava.exception.EntityNotFoundException;
import it.jac.corsojava.service.DipendenteService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/dipendente")
public class DipendenteRESTController {

	private static Logger log = LogManager.getLogger(DipendenteRESTController.class);	

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Dipendente dipendente) {

		log.info("Creo un nuovo dipendente");

//		controllo che timbro abbia i campi valorizzati in modo corretto
		LocalDate dataNascita = dipendente.getDataNascita();
		if (dataNascita == null) {
			
			return Response.status(Status.BAD_REQUEST)
					.entity("Timbro non valorizzato")
					.header("Content-Type", "text/plain")
					.build();
		}
		
		String sesso = dipendente.getSesso();
		if (sesso == null || sesso.trim().length() == 0) {
			
			return Response.status(Status.BAD_REQUEST)
					.entity("Sesso non valorizzato")
					.header("Content-Type", "text/plain")
					.build();
		}
		
		String email = dipendente.getEmail();
		if (email == null || email.trim().length() == 0) {
			
			return Response.status(Status.BAD_REQUEST)
					.entity("Email non valorizzato")
					.header("Content-Type", "text/plain")
					.build();
		}
		
		String password = dipendente.getPassword();
		if (password == null || password.trim().length() == 0) {
			
			return Response.status(Status.BAD_REQUEST)
					.entity("Sesso non valorizzato")
					.header("Content-Type", "text/plain")
					.build();
		}
		
		String nome = dipendente.getNome();
		if (nome == null || nome.trim().length() == 0) {
			
			return Response.status(Status.BAD_REQUEST)
					.entity("Nome non valorizzato")
					.header("Content-Type", "text/plain")
					.build();
		}
		
		String cognome = dipendente.getCognome();
		if (cognome == null || cognome.trim().length() == 0) {
			
			return Response.status(Status.BAD_REQUEST)
					.entity("Cognome non valorizzato")
					.header("Content-Type", "text/plain")
					.build();
		}
		
		Dipendente result = DipendenteService.getInstance().create(
			nome, cognome, dataNascita, sesso, email, password, dipendente.getIdAzienda(), dipendente.getIdBusinessUnit());
		
		log.info("Timbro creato con successo");
		
		return Response.ok(result).build();
	}
	
	public Response update(@PathParam("id") long id, Dipendente dipendenteData) {

		log.info("Modifico dipendente [id={}]", id);
		
		DipendenteService.getInstance().update(dipendenteData.getNome(), dipendenteData.getCognome(), dipendenteData.getDataNascita(), dipendenteData.getSesso(), dipendenteData.getEmail(), dipendenteData.getPassword(), id);
		
		return Response.ok().build();
	}
	
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Dipendente> findAll() {

		log.info("Ricerco l'elenco dei dipendenti");
		
		List<Dipendente> list = DipendenteService.getInstance().findAll();
		
		return list;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Dipendente findById(@PathParam("id") long id) {

		log.info("Ricerco dipendente [id={}]", id);
		
		Dipendente user = DipendenteService.getInstance().findById(id);
		if (user == null) {
			
			throw new EntityNotFoundException("Dipendente not found");
		}
		return user;
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") long id) {

		log.info("Elimino dipendente [id={}]", id);
		
		DipendenteService.getInstance().delete(id);
		
		return Response.ok().build();
	}

}
