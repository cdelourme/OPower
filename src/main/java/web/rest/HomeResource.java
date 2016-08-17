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

import domain.Home;
import repository.HomeDAOImpl;


@Path("/home")
public class HomeResource {
	HomeDAOImpl homeDao = new HomeDAOImpl();
	
	@GET 
    @Produces({ MediaType.APPLICATION_JSON })
	public List<Home> getAll(){
		System.out.println("GetAll() Home");
		return homeDao.findAll();
	}
	
	@GET @Path("search/{id}") 
	@Produces({ MediaType.APPLICATION_JSON })
	public Home getById(@PathParam("id") String value){
		return homeDao.findById(value);
	}
	
	@GET @Path("search/person/{id}") 
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Home> getByPersonId(@PathParam("id") String value){
		return homeDao.findByPersonId(value);
	}

	@DELETE @Path("delete/{id}") 
	public void deleteById(@PathParam("id") String value) { 
		System.out.println("DELETE");
		Home p2 = homeDao.findById(value);
		if (p2 != null)
			homeDao.delete(p2);		
	} 

	@POST 
    public void createHome(@FormParam("nom") String nom,
            @FormParam("taille") int taille,
            @FormParam("nombrePiece") int nombrePiece) { 
		System.out.println("POST de Home"); 
		System.out.println(nom +","+ taille +","+ nombrePiece); 
		homeDao.createHome(nom,taille,nombrePiece);
    } 

	@POST @Path("{id}/heater/")
    public void createHomeHeater(@PathParam("id") String id,
    		@FormParam("nom") String nom,
            @FormParam("consoMoyenne") int consoMoyenne) { 
		System.out.println("POST de Home/heater"); 
		System.out.println(nom +","+ consoMoyenne); 
		homeDao.createHomeHeater(id, nom, consoMoyenne);
    }
	
	@POST @Path("{id}/Device/")
    public void createHomeDevice(@PathParam("id") String id,
    		@FormParam("nom") String nom,
            @FormParam("consoMoyenne") int consoMoyenne) { 
		System.out.println("POST de Home/Device"); 
		System.out.println(nom +","+ consoMoyenne); 
		homeDao.createHomeDevice(id, nom, consoMoyenne);
    }
	
	@PUT
    @Consumes({ MediaType.APPLICATION_JSON }) 
    public void createUpdateHome( Home p) { 
		System.out.println("PUT de Home ...");
		Home p2 = homeDao.findById(p.getId());
		if (p2 != null){
			System.out.println("		-> Update");
			p2=p;
			homeDao.update(p2);
		}
		else
			System.out.println("		-> Creation");
			// PB : est appelé après un update !!!
			//personDao. createPerson(p.getNom(),p.getPrenom(),p.getMail());
    } 
	
	@POST
    @Consumes({ MediaType.APPLICATION_JSON }) 
    public void createHome( Home p) { 
		homeDao.createHome(p.getName(),p.getTaille(),p.getNombrePiece());
    } 
}
