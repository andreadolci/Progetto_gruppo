package it.jac.corsojava.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.entity.Timbro;
import it.jac.corsojava.exception.EntityNotFoundException;
import it.jac.corsojava.service.TimbroService;
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

@Path("/timbro")
public class TimbroRESTController {

	private static Logger log = LogManager.getLogger(TimbroRESTController.class);	

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Timbro timbro) {

		log.info("Creo un nuovo timbro");

//		controllo che timbro abbia i campi valorizzati in modo corretto
		LocalDateTime dataOra = timbro.getDataOra();
		if (dataOra == null) {
			
			return Response.status(Status.BAD_REQUEST)
					.entity("dataOra non valorizzato")
					.header("Content-Type", "text/plain")
					.build();
		}
		
		String tipo = timbro.getTipo();
		if (tipo == null || tipo.trim().length() == 0) {
			
			return Response.status(Status.BAD_REQUEST)
					.entity("Tipo non valorizzata")
					.header("Content-Type", "text/plain")
					.build();
		}
		
		Timbro result = TimbroService.getInstance().create(
			dataOra, tipo, timbro.getIdAzienda(), timbro.getIdDipendente());
		
		log.info("Timbro creato con successo");
		
		return Response.ok(result).build();
	}
	
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Timbro> findAll() {

		log.info("Ricerco l'elenco dei timbri");
		
		List<Timbro> list = TimbroService.getInstance().findAll();
		
		return list;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Timbro findById(@PathParam("id") long id) {

		log.info("Ricerco timbro [id={}]", id);
		
		Timbro user = TimbroService.getInstance().findById(id);
		if (user == null) {
			
			throw new EntityNotFoundException("Timbro not found");
		}
		return user;
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") long id) {

		log.info("Elimino timbro [id={}]", id);
		
		TimbroService.getInstance().delete(id);
		
		return Response.ok().build();
	}

}
