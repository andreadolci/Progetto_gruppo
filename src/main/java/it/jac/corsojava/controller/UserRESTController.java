package it.jac.corsojava.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.entity.Azienda;
import it.jac.corsojava.exception.EntityNotFoundException;
import it.jac.corsojava.service.AziendaService;
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

@Path("/users")
public class UserRESTController {

	private static Logger log = LogManager.getLogger(UserRESTController.class);	

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Azienda user) {

		log.info("Creo un nuovo utente");

//		controllo che lo user abbia i campi valorizzati in modo corretto
		String username = user.getUsername();
		if (username == null || username.trim().length() == 0) {
			
			return Response.status(Status.BAD_REQUEST)
					.entity("Username non valorizzato")
					.header("Content-Type", "text/plain")
					.build();
		}
		
		String password = user.getPassword();
		if (password == null || password.trim().length() == 0) {
			
			return Response.status(Status.BAD_REQUEST)
					.entity("Password non valorizzata")
					.header("Content-Type", "text/plain")
					.build();
		}
		
		Azienda result = AziendaService.getInstance().create(
			username, user.getNome(), user.getCognome(), password);
		
		log.info("Utente creato con successo");
		
		return Response.ok(result).build();
	}
	
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Azienda> findAll() {

		log.info("Ricerco l'elenco degli utenti");
		
		List<Azienda> list = AziendaService.getInstance().findAll();
		
		return list;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Azienda findById(@PathParam("id") long id) {

		log.info("Ricerco utente [id={}]", id);
		
		Azienda user = AziendaService.getInstance().findById(id);
		if (user == null) {
			
			throw new EntityNotFoundException("User not found");
		}
		return user;
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") long id, Azienda userData) {

		log.info("Modifico utente [id={}]", id);
		
		AziendaService.getInstance().update(id, userData.getNome(), userData.getCognome());
		
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") long id) {

		log.info("Elimino utente [id={}]", id);
		
		AziendaService.getInstance().delete(id);
		
		return Response.ok().build();
	}

}
