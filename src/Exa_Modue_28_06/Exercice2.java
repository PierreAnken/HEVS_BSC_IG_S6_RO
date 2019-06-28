package Exa_Modue_28_06;

import baseObjects.Graphe;

public class Exercice2 {

	public static void main(String[] args) {
		int distancier[][] = {
				{0,0,0,0},
				{76,0,0,0},
				{36,109,0,0},
				{81,34,114,0}
			};
		
		//etant donné qu'un nouveau constructeur est demandé il ne peut pas avoir la même signature que l'habituel
		Graphe graphe = new Graphe(distancier,true);
		graphe.afficheFilesDistances();
	}

}
