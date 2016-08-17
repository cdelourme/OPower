package domain;

import java.io.Serializable;

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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
@Table(name = "ELECTRONICDEVICE")
@XmlRootElement
public class ElectronicDevice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2318686645924615059L;
	private String id;
	private String name;
	private int consoMoyenne;
	private Home home;
	
	public ElectronicDevice(){
		
	}
	public ElectronicDevice(String name, int consoMoyenne) {
		super();
		this.name = name;
		this.consoMoyenne = consoMoyenne;
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
	@Basic
	@Column(name = "CONSO")
	public int getConsoMoyenne() {
		return consoMoyenne;
	}
	public void setConsoMoyenne(int consoMoyenne) {
		this.consoMoyenne = consoMoyenne;
	}

	@ManyToOne//(optional = true,cascade=CascadeType.PERSIST)
	@XmlTransient
	public Home getHome(){
		return home;
	}
	public void setHome(Home h){
		home = h;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + consoMoyenne;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ElectronicDevice other = (ElectronicDevice) obj;
		if (consoMoyenne != other.consoMoyenne)
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
		return true;
	}
	@Override
	public String toString() {
		return "Heater [id=" + id + ", name=" + name + ", consoMoyenne=" + consoMoyenne + "]";
	}
	
}
