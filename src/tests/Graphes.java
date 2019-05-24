package tests;

import baseObjects.Graphe;

public class Graphes {

	public static void main(String[] args) {

		int matriceD[][] = {{1, 2, 3},
				{4},
				{4,5,6},
				{6},
				{7},
				{7},
				{7},
				{}};

			
		Graphe graphe = new Graphe(matriceD);
		graphe.afficheFiles();

		graphe.exploreFile(0);
		graphe.afficheFiles();

		graphe.exploreFile(0);
		graphe.afficheFiles();

		graphe.afficheChemin(0,7);
	}

}
