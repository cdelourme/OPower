package web.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import domain.Heater;
import domain.Person;
import repository.HeaterDAOImpl;
import repository.PersonDAOImpl;

@Path("/heater")
public class HeaterResource {
	HeaterDAOImpl heaterDao = new HeaterDAOImpl();
	
	@GET 
    @Produces({ MediaType.APPLICATION_JSON })
	public List<Heater> getAll(){
		System.out.println("GetAll() Person");
		return heaterDao.findAll();
	}
	
	@GET @Path("search/{id}") 
	@Produces({ MediaType.APPLICATION_JSON })
	public Heater getById(@PathParam("id") String value){
		return heaterDao.findById(value);
	}

	@DELETE @Path("delete/{id}") 
	public void deleteById(@PathParam("id") String value) { 
		System.out.println("DELETE");
		Heater p2 = heaterDao.findById(value);
		if (p2 != null)
			heaterDao.delete(p2);		
	} 
	
	@POST 
    public void createHeater(@FormParam("nom") String nom,
            @FormParam("consoMoyenne") int consoMoyenne) { 
		System.out.println("POST de Heater"); 
		System.out.println(nom +","+ consoMoyenne); 
		heaterDao. createHeater(nom,consoMoyenne);
    } 
	
	@PUT
    @Consumes({ MediaType.APPLICATION_JSON }) 
    public void createUpdateHeater( Heater p) { 
		System.out.println("PUT de Heater ...");
		Heater p2 = heaterDao.findById(p.getId());
		if (p2 != null){
			System.out.println("		-> Update");
			p2=p;
			heaterDao.update(p2);
		}
		else
			System.out.println("		-> Creation");
			// PB : est appelé après un update !!!
			//heaterDao. createHeater(p.getName(),p.getConsoMoyenne());
    } 
	
	@POST
    @Consumes({ MediaType.APPLICATION_JSON }) 
    public void createHeater( Heater h) { 
		heaterDao.createHeater(h.getName(),h.getConsoMoyenne());
    } 
}
