package tests;

import baseObjects.Graphe;

public class ComptageArc {

	public static void main(String[] args) {
		int matrice[][] = {
				{3,5},
				{0,3,2},
				{3,4},
				{4},
				{},
				{0,4}
		};
		
		Graphe graphe = new Graphe(matrice);
		graphe.afficheFiles();
		graphe.getArcs();

	}

}
