package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import play.data.validation.Email;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Utilisateur extends Model {
	
	@Required
	@Email
	public String email;
	
	@Required
	public String motDePasse;
	
	@ManyToMany
	public List<Manga> mangas = new ArrayList<Manga>();
	
	public Utilisateur(String email, String motDePasse){
		this.email = email;
		this.motDePasse = motDePasse;
	}
	
	public static Utilisateur connect(String email, String motDePasse){
		return find("byEmailAndMotDePasse",email, motDePasse).first();		
	}
	
	public static List<Serie> mesSeries(){
		return find("select distinct m.serie from Utilisateur u join u.mangas m").fetch();
	}
	
	public static List<Auteur> mesAuteurs(){
		return find("select distinct s.auteurs from Utilisateur u join u.mangas m join m.serie s").fetch();
	}
	
	public static List<Manga> mesMangasDeLaSerie(Serie serie){
		//return find("select distinct m from Utilisateur u join u.mangas m join m.serie s where s.id = ?", serie.id).fetch();
		return find("select distinct manga from Utilisateur utilisateur join utilisateur.mangas manga join manga.serie serie where serie.id = ?", serie.id).fetch();
	}
}