package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

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
	
	@Required
	public boolean isAdmin;  
	
	@ManyToMany
	public List<Manga> mangas = new ArrayList<Manga>();
	
	public Utilisateur(String email, String motDePasse){
		this.email = email;
		this.motDePasse = motDePasse;
	}
	
	public static Utilisateur connect(String email, String motDePasse){
		return find("byEmailAndMotDePasse",email, motDePasse).first();		
	}
	
	public static List<Serie> findAllSeries(Utilisateur utilisateur){
		return find("select distinct m.serie from Utilisateur u join u.mangas m where u.id = ?", utilisateur.id).fetch();
	}
	
	public static List<Auteur> findAllAuteurs(Utilisateur utilisateur){
		return find("select distinct s.auteurs from Utilisateur u join u.mangas m join m.serie s where u.id = ?", utilisateur.id).fetch();
	}
	
	public static List<Manga> findAllMangaOfSerie(Utilisateur utilisateur, Serie serie){		
		return find("select distinct m from Utilisateur u join u.mangas m join m.serie s where s.id = ? and u.id = ?", serie.id, utilisateur.id).fetch();
	}
	
	public static List<Manga> findAllMangaOfAuteur(Utilisateur utilisateur, Auteur auteur){
		return find("select distinct m from Utilisateur u join u.mangas m join m.serie s join s.auteurs a where a.id = ? and u.id = ?", auteur.id, utilisateur.id).fetch();
	}
	
	public static List<Manga> findAllMangaOfGenre(Utilisateur utilisateur, Genre genre){
		return find("select distinct m from Utilisateur u join u.mangas m join m.serie s join s.genre g where g.id = ? and u.id = ?", genre.id, utilisateur.id).fetch();
	}
	
	public static boolean isMySerieComplete(Utilisateur utilisateur, Serie serie){
		return (Manga.findAllMangaBySerie(serie).size() == Utilisateur.findAllMangaOfSerie(utilisateur, serie).size()); 
	}
	
	public static List<Manga> findMissingMangasInSerie(Utilisateur utilisateur, Serie serie){
		return find("select distinct m from Manga m join m.serie s where m.id not in (select distinct manga.id from Utilisateur u join u.mangas manga join manga.serie serie where serie.id = ? and u.id = ? ) and s.id = ?", serie.id, utilisateur.id, serie.id).fetch();
	}
	
	public String toString(){
		return this.email;
	}
}