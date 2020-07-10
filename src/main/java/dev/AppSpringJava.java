package dev;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dev.config.AppConfig;
import dev.ihm.Menu;

public class AppSpringJava {

	public static void main(String[] args) {
		
		// Création du contexte Spring à partir d'une configuration Java
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		
		//définition des profils actifs
		context.getEnvironment().setActiveProfiles("memoire","V2");
		context.register(AppConfig.class);
		context.refresh();
		
		// récupération du bean Menu
		Menu menu = context.getBean(Menu.class);
		menu.afficher();
		// fermeture du Scanner
		context.getBean(Scanner.class).close();
		// fermeture du contexte Spring
		context.close();
		
	}

}
