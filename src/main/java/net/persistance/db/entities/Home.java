package net.persistance.db.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "HOME")
@XmlRootElement
public class Home implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6392613119419452404L;
	private String id;
	private String name;
	private int taille;
	private int nombrePiece;
	private Person person;
	private List<Heater> heaters;
	private List<ElectronicDevice> devices;
	
	public Home(){
	}

	public Home(String name, int taille, int nombrePiece) {
		super();
		this.name = name;
		this.taille = taille;
		this.nombrePiece = nombrePiece;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Basic
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlTransient
	@OneToMany(mappedBy = "home",cascade=CascadeType.PERSIST,fetch = FetchType.LAZY)
	public List<Heater> getHeaters() {
		return heaters;
	}
	public void setHeaters(List<Heater> heaters) {
		this.heaters = heaters;
	}
	public void addHeater(Heater h){
		if (heaters == null){
			heaters = new ArrayList<Heater>();
		}
		heaters.add(h);
		h.setHome(this);
	}
	@Basic
	@Column(name = "TAILLE")
	public int getTaille() {
		return taille;
	}
	public void setTaille(int taille) {
		this.taille = taille;
	}
	@Basic
	@Column(name = "NOMBREPIECE")
	public int getNombrePiece() {
		return nombrePiece;
	}
	public void setNombrePiece(int nombrePiece) {
		this.nombrePiece = nombrePiece;
	}
	
	@OneToMany(mappedBy = "home", cascade=CascadeType.PERSIST,fetch = FetchType.LAZY)
	public List<ElectronicDevice> getDevices() {
		return devices;
	}
	public void setDevices(List<ElectronicDevice> devices) {
		this.devices = devices;
	}
	public void addDevice(ElectronicDevice e){
		if (devices == null){
			devices = new ArrayList<ElectronicDevice>();
		}
		devices.add(e);
		e.setHome(this);
	}
	@ManyToOne
	@JoinColumn(name="PersonId")
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((heaters == null) ? 0 : heaters.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + nombrePiece;
		result = prime * result + taille;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Home other = (Home) obj;
		if (heaters == null) {
			if (other.heaters != null)
				return false;
		} else if (!heaters.equals(other.heaters))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nombrePiece != other.nombrePiece)
			return false;
		if (taille != other.taille)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Home [id=" + id + ", name=" + name + ", taille=" + taille + ", nombrePiece=" + nombrePiece
				+ ", heaters=" + heaters + "]";
	}
	
	
}
