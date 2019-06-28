package Exa_Modue_28_06;

import baseObjects.File;
import baseObjects.Info;
import baseObjects.Noeud;

public class Exercice1 {

	public static void main(String[] args) {
		
		
		System.out.println("Nombre 19");
		File nombre19 = new File();
		nombre19.enfile(new Noeud(new Info(1)));
		nombre19.enfile(new Noeud(new Info(0)));
		nombre19.enfile(new Noeud(new Info(0)));
		nombre19.enfile(new Noeud(new Info(1)));
		nombre19.enfile(new Noeud(new Info(1)));
		nombre19.affiche();
		
		nombre19.incremente();
		
		nombre19.affiche();
		System.out.println("\nNombre 3");
		File nombre3 = new File();
		nombre3.enfile(new Noeud(new Info(1)));
		nombre3.enfile(new Noeud(new Info(1)));
		nombre3.affiche();
		
		nombre3.incremente();
		
		nombre3.affiche();
		
	}

}
