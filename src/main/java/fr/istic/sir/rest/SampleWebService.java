package fr.istic.sir.rest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.persistance.db.entities.ElectronicDevice;
import net.persistance.db.entities.Heater;
import net.persistance.db.entities.Home;
import net.persistance.db.entities.Person;
import net.persistance.db.tpjpa3.PersonDAOImpl;

@Path("/OPower")
public class SampleWebService {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "<H3>Serveur de données OPower.</H3>";
		}
	
	@GET
	@Path("/person_old")
	@Produces( MediaType.APPLICATION_JSON )
	public Person getPerson() {
		Heater heat1 = new Heater("Salon",1000);
		Heater heat2 = new Heater("Cuisine",750);
		
		ElectronicDevice ed = new ElectronicDevice("SecheLinge",5000);
		ElectronicDevice ed2 = new ElectronicDevice("Fraiseuse",10000);
		
		Home h1 = new Home("Chateau",250,7);
		Home h2 = new Home("La banque",1120,14);
		
		h1.addHeater(heat1);
		h1.addHeater(heat2);
		
		h1.addDevice(ed);
		h1.addDevice(ed2);
		
		PersonDAOImpl persDao = new PersonDAOImpl();
		Person p = persDao.createPerson("Oncle","Picsou","oncle.picsou@flagada.jones");
		p.setNom("Donald");
				
		p.addHome(h1);
		p.addHome(h2);
		
		persDao.update(p);
		return p;
	}
}

