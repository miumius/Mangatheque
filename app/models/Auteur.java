package models;

import javax.persistence.Entity;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Auteur extends Model {

	@Required
	public String nom;
	
}
