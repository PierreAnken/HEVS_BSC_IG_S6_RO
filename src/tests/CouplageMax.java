package tests;

import baseObjects.Graphe;

public class CouplageMax {

	public static void main(String[] args) {
		
		//ouvriers	a 	b	c	d	e	f	g
		//machines	1, 2, 4, 6 |2, 3, 4 | 1, 3|2, 3, 5| 2, 4| 1, 3, 5|1, 2, 3

		int competencesOuvrier[][] = {
				{1, 2, 4,6},
				{2,3,4},
				{1,3},
				{2,3,4},
				{2,4},
				{1,3,5},
				{1,2,3},
		};
		
		Graphe graph = new Graphe(competencesOuvrier);
		graph.afficheFiles();
		graph.couplageSimple();
		graph.afficheFiles();
		
	}

}
