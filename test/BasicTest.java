import org.junit.*;

import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

	@Before
    public void setup() {
        Fixtures.deleteDatabase();
        Fixtures.loadModels("data.yml");
    }
	
    @Test
    public void connexionUtilisateur() {
        assertNotNull(Utilisateur.connect("cedric.lemius@gmail.com", "secret"));
    }
    
    @Test
    public void trouverLesSeriesDeUtilisateur() {
    	Utilisateur moi = Utilisateur.connect("cedric.lemius@gmail.com", "secret");
    	assertEquals(3, Utilisateur.findAllSeries(moi).size());
    }
    

    @Test
    public void trouverLesAuteursDeUtilisateur() {
    	Utilisateur moi = Utilisateur.connect("cedric.lemius@gmail.com", "secret");
    	assertEquals(3, Utilisateur.findAllAuteurs(moi).size());
    }
    
    @Test
    public void trouverLesMangasParSerieDeUtilisateur() {
    	Utilisateur moi = Utilisateur.connect("cedric.lemius@gmail.com", "secret");
    	
    	Serie serie = Serie.find("byTitre", "Naruto").first();
    	assertEquals(1, Utilisateur.findAllMangaOfSerie(moi, serie).size());
    	
    	serie = Serie.find("byTitre", "One Piece").first();
    	assertEquals(2, Utilisateur.findAllMangaOfSerie(moi, serie).size());
    	
    	serie = Serie.find("byTitre", "Berserk").first();
    	assertEquals(3, Utilisateur.findAllMangaOfSerie(moi, serie).size());
    }
    
    @Test
    public void trouverLesMangasParAuteurDeUtilisateur() {
    	Utilisateur moi = Utilisateur.connect("cedric.lemius@gmail.com", "secret");
    	
    	Auteur auteur = Auteur.find("byNom", "Eiichirō Oda").first();
    	assertEquals(2, Utilisateur.findAllMangaOfAuteur(moi, auteur).size());
    	
    	auteur = Auteur.find("byNom", "Masashi Kishimoto").first();
    	assertEquals(1, Utilisateur.findAllMangaOfAuteur(moi, auteur).size());
    	
    	auteur = Auteur.find("byNom", "Kentaro Miura").first();
    	assertEquals(3, Utilisateur.findAllMangaOfAuteur(moi, auteur).size());
    }
    
    @Test
    public void trouverLesMangasParGenreDeUtilisateur() {
    	Utilisateur moi = Utilisateur.connect("cedric.lemius@gmail.com", "secret");
    	
    	Genre genre = Genre.find("byNom", "Shōnen").first();
    	assertEquals(3, Utilisateur.findAllMangaOfGenre(moi, genre).size());
    	
    	genre = Genre.find("byNom", "Seinen").first();
    	assertEquals(3, Utilisateur.findAllMangaOfGenre(moi, genre).size());
    }
        
    @Test
    public void trouverLesMangasManquantDansUneSerie() {
    	Utilisateur moi = Utilisateur.connect("cedric.lemius@gmail.com", "secret");
    	
    	Serie serie = Serie.find("byTitre", "Naruto").first();
    	assertEquals(4, Utilisateur.findMissingMangasInSerie(moi, serie).size());
    	
    	serie = Serie.find("byTitre", "One Piece").first();
    	assertEquals(2, Utilisateur.findMissingMangasInSerie(moi, serie).size());
    	
    	serie = Serie.find("byTitre", "Berserk").first();
    	assertEquals(0, Utilisateur.findMissingMangasInSerie(moi, serie).size());
    }
    
    @Test
    public void listeDesMangaDuneSerie(){
    	Serie serie = Serie.find("byTitre", "Naruto").first();
    	assertEquals(5, Manga.findAllMangaBySerie(serie).size());
    	
    	serie = Serie.find("byTitre", "One Piece").first();
    	assertEquals(4, Manga.findAllMangaBySerie(serie).size());
    	
    	serie = Serie.find("byTitre", "Berserk").first();
    	assertEquals(3, Manga.findAllMangaBySerie(serie).size());
    }
    
    @Test
    public void listeDesMangaParGenre() {
    	Genre genre = Genre.find("byNom", "Shōnen").first();
    	assertEquals(9, Manga.findAllMangaByGenre(genre).size());
    	
    	genre = Genre.find("byNom", "Seinen").first();
    	assertEquals(3, Manga.findAllMangaByGenre(genre).size());
    }
}
