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

import domain.ElectronicDevice;
import domain.Heater;
import domain.Person;
import repository.DeviceDAOImpl;
import repository.HeaterDAOImpl;
import repository.PersonDAOImpl;

@Path("/device")
public class DeviceResource {
	DeviceDAOImpl deviceDao = new DeviceDAOImpl();
	
	@GET 
    @Produces({ MediaType.APPLICATION_JSON })
	public List<ElectronicDevice> getAll(){
		System.out.println("GetAll() Person");
		return deviceDao.findAll();
	}
	
	@GET @Path("search/{id}") 
	@Produces({ MediaType.APPLICATION_JSON })
	public ElectronicDevice getById(@PathParam("id") String value){
		return deviceDao.findById(value);
	}

	@DELETE @Path("delete/{id}") 
	public void deleteById(@PathParam("id") String value) { 
		System.out.println("DELETE");
		ElectronicDevice p2 = deviceDao.findById(value);
		if (p2 != null)
			deviceDao.delete(p2);		
	} 
	
	@POST 
    public void createDevice(@FormParam("nom") String nom,
            @FormParam("consoMoyenne") int consoMoyenne) { 
		System.out.println("POST de ElectronicDevice"); 
		System.out.println(nom +","+ consoMoyenne); 
		deviceDao. createDevice(nom,consoMoyenne);
    } 
	
	@PUT
    @Consumes({ MediaType.APPLICATION_JSON }) 
    public void createUpdateDevice( ElectronicDevice p) { 
		System.out.println("PUT de ElectronicDevice ...");
		ElectronicDevice p2 = deviceDao.findById(p.getId());
		if (p2 != null){
			System.out.println("		-> Update");
			p2=p;
			deviceDao.update(p2);
		}
		else
			System.out.println("		-> Creation");
			// PB : est appelé après un update !!!
			//heaterDao. createHeater(p.getName(),p.getConsoMoyenne());
    } 
	
	@POST
    @Consumes({ MediaType.APPLICATION_JSON }) 
    public void createDevice( ElectronicDevice h) { 
		deviceDao.createDevice(h.getName(),h.getConsoMoyenne());
    } 
}
