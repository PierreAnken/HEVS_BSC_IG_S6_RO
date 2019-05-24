package CC24_05;

import baseObjects.Graphe;

public class Exercice3 {

	public static void main(String[] args) {
		
		//test avec l'exemple donné
		int matriceSuccesseurs[][] = {
				
				{1}, 		//A0 
				{2,4,5},	//B1
				{3,6},		//C2
				{2,7},		//D3
				{0,5},		//E4
				{6},		//F5
				{5},		//G6 
				{3,6},		//H7
			};
		
		Graphe graphe = new Graphe(matriceSuccesseurs);
		graphe.fortementConnexe(2,4); // Sommet C2 <=> E4 - réponse non
		graphe.fortementConnexe(2,7); // Sommet C2 <=> H7 - réponse oui
		graphe.fortementConnexe(1,0); // Sommet B1 <=> A0 - réponse oui
		graphe.fortementConnexe(3,6); // Sommet D3 <=> G6 - réponse non
	}

}
