package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class Serie extends Model {
	
	@Required
	public String titre;
	
	@Required
	@ManyToMany
	public List<Auteur> auteurs = new ArrayList<Auteur>();
	
	@Required
	@ManyToOne
	public Genre genre;	
	
	@Required
	@Lob
	public Blob illustration;
	
	public String toString(){
		return this.titre; 
	}
}
