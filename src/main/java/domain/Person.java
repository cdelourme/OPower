package domain;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonManagedReference;
import org.eclipse.persistence.annotations.CascadeOnDelete;


@Entity
@Table(name = "PERSON")
@XmlRootElement
public class Person implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2122931725428029151L;
	private String id;
	private String nom;
	private String prenom;
	private String mail;
	private List<Home> homes;

	public Person(){
	}

	public Person(String nom, String prenom, String mail) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		if (homes == null){
			homes = new ArrayList<Home>();
		}
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
	@Column(name = "NOM")
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Basic
	@Column(name = "PRENOM")
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Basic
	@Column(name = "MAIL", unique=true)
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@OneToMany(mappedBy = "person", cascade=CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval=true)
	@XmlElement(type=Home.class)
	public List<Home> getHomes() {
		return homes;
	}

	public void setHomes(List<Home> homes) {
		this.homes = homes;
	}
	
	public void addHome(Home h){
		if (homes == null){
			homes = new ArrayList<Home>();
		}
		homes.add(h);
		h.setPerson(this);
	}
	
	public void removeHome(Home h){
		if (homes != null){
		homes.remove(h);
		}
	}
	
	public void update( Person p ){
		if (p != null){
			this.setNom(p.getNom());
			this.setPrenom(p.getPrenom());
			this.setMail(p.getMail());
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((homes == null) ? 0 : homes.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
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
		Person other = (Person) obj;
		if (homes == null) {
			if (other.homes != null)
				return false;
		} else if (!homes.equals(other.homes))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", homes=" + homes + "]";
	}
	
	
}
