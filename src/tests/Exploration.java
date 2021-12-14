package tests;

import baseObjects.Graphe;

public class Exploration {

	public static void main(String[] args) {
		
		int matrice[][] = {
				{1, 2, 3}, 	// Le noeud 0 à un lien vers 1-2-3
				{4},		// Le noeud 1 à un lien vers 4
				{4,5,6},
				{6},
				{7},
				{7},
				{7},
				{}
		};

			
		Graphe graphe = new Graphe(matrice);
		graphe.explorationSimple(0);
		graphe.afficheFiles();
	}

}
