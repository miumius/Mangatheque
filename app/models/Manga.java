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
	
	public static List<Manga> findAllMangaBySerie(Serie serie){
		return find("select distinct m from Manga m join m.serie s where s.id = ?", serie.id).fetch();
	}
	
	public static List<Manga> findAllMangaByGenre(Genre genre){
		return find("select distinct m from Manga m join m.serie s join s.genre g where g.id = ?", genre.id).fetch();
	}
	
	public static List<Manga> findAllMangaByAuteur(Auteur auteur){
		return find("select distinct m from Manga m join m.serie s join s.auteurs a where a.id = ?", auteur.id).fetch();
	}
	
	public String toString(){
		return this.serie.titre + " " + this.volume;
	}
}
