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
    public void findMesSeries() {
    	Utilisateur moi = Utilisateur.connect("cedric.lemius@gmail.com", "secret");
    	assertEquals(3, moi.mesSeries().size());
    }
    

    @Test
    public void findMesAuteurs() {
    	Utilisateur moi = Utilisateur.connect("cedric.lemius@gmail.com", "secret");
    	assertEquals(3, moi.mesAuteurs().size());
    }
    
    @Test
    public void findMesMangaParSerie() {
    	Utilisateur moi = Utilisateur.connect("cedric.lemius@gmail.com", "secret");
    	Serie serie = Serie.find("byTitre", "Naruto").first();
    	assertEquals(3, moi.mesMangasDeLaSerie(serie).size());
    }
}
