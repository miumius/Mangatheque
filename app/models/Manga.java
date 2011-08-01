package models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class Manga extends Model {
	
	@Required
	@ManyToOne
	public Serie serie;
		
	@Required
	public Integer volume;
	
	@Lob
	public Blob couverture;
	
}
