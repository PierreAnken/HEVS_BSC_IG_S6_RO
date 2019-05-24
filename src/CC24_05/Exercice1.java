package CC24_05;

import baseObjects.File;
import baseObjects.Info;
import baseObjects.Noeud;

public class Exercice1 {

	public static void main(String[] args) {
		
		File fileTest = new File();
		fileTest.enfile(new Noeud(new Info(3)));
		fileTest.enfile(new Noeud(new Info(6)));
		fileTest.enfile(new Noeud(new Info(2)));
		fileTest.enfile(new Noeud(new Info(1)));
		fileTest.enfile(new Noeud(new Info(9)));
		fileTest.enfile(new Noeud(new Info(8)));
		fileTest.enfile(new Noeud(new Info(4)));
		
		fileTest.affiche();
		
		fileTest.inverse();
		
		fileTest.affiche();
		

	}

}
