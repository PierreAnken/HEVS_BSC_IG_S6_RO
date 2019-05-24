package CC24_05;

import baseObjects.Graphe;

public class Exercice3 {

	public static void main(String[] args) {
		
		//test avec l'exemple donn�
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
		graphe.fortementConnexe(2,4); // Sommet C2 <=> E4 - r�ponse non
		graphe.fortementConnexe(2,7); // Sommet C2 <=> H7 - r�ponse oui
		graphe.fortementConnexe(1,0); // Sommet B1 <=> A0 - r�ponse oui
		graphe.fortementConnexe(3,6); // Sommet D3 <=> G6 - r�ponse non
	}

}
