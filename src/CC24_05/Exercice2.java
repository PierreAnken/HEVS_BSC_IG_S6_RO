package CC24_05;

import baseObjects.Graphe;

public class Exercice2 {

	public static void main(String[] args) {
		
		int matriceSuccesseurs[][] = {
							{1, 2},
							{2,3},
							{4},
							{2, 4, 5},
							{},
							{4}
						};

		int matriceDistances[][] =  {
							{4, 2},
							{-7,5},
							{10},
							{-10, 2, -6},
							{},
							{3},
						};

		Graphe graphe = new Graphe(matriceSuccesseurs, matriceDistances);
		graphe.afficheSuccEtDist();
		graphe.affichePlusPetiteArrete();

	}

}
