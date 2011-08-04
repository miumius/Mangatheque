package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import controllers.Secure.Security;

import models.*;

@With(Secure.class)
public class Application extends Controller {
	
	public static void index(){
		Utilisateur utilisateur = Utilisateur.find("byEmail", Security.connected()).first();
		List<Serie> series = Utilisateur.findAllSeries(utilisateur);
		render(series, utilisateur);
	}
}