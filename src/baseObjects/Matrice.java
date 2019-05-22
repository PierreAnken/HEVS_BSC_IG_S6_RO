package baseObjects;

public class Matrice {
	int n;		// nombre de ligne
	int col;	// nombre de colonnes
	File liste[] ;

	public Matrice(){
		n=0;
		col=0;
	}
	public void matrice(int n, int col){
		this.n=n;
		this.col=col;
		this.liste = new File [n];
		for (int i = 0; i < n; i++) 
			liste[i] = new File();
	}
	public Matrice(int[][] m1) {
		// tableau de file nombre de lignes
		matrice(m1.length,m1[0].length);

		for (int i = 0; i < n; i++) {
			liste[i] = new File();
			for (int j = 0; j < col; j++) {
				// si valeur <> 0 alors enfile
				if(m1[i][j] != 0){
					liste[i].enfile(new Noeud(new Info(m1[i][j],j)));
					//			System.out.println("m1["+i+"]["+j+"]="+m1[i][j]);
				}
			}
		}
	}

	public void afficheMatrice() {
		int valeur = 0;

		for (int i = 0; i < n; i++) {
			System.out.println("");	
			System.out.print("ligne "+i+": ");	
			Noeud courant = liste[i].getPremier();

			for (int j = 0; j < col; j++) {
				// si colonne 
				if(courant != null && courant.getInfo().getColonne() == j){
					valeur = courant.getInfo().getValeur();
					courant = courant.getSuivant();
				}
				else{
					valeur = 0;
				}
				System.out.print("\t "+valeur);
			}
		}

	}

	public void afficheFiles() {
		System.out.println("\n----- Affiche files -----");
		for (int i = 0; i < liste.length; i++) {
			System.out.print("file "+i+": \t");
			//liste[i].affiche();

			Noeud courant = liste[i].getPremier() ;

			while (courant != null)
			{
				System.out.print(courant.getInfo().toString()+ " ");
				courant = courant.getSuivant() ;
			}
			System.out.println();	
		}
	}

	public Matrice addition(Matrice matrice2) {

		for (int i = 0; i < n; i++) {
			Noeud courant = liste[i].getPremier();
			Noeud courant2 = matrice2.liste[i].getPremier();
			File file3 = new File();

			while (!liste[i].estVide() && !matrice2.liste[i].estVide())
			{
				// si meme colonne
				if(courant.getInfo().getColonne() == courant2.getInfo().getColonne()){
					// addition valeurs
					int newValeur = courant.getInfo().getValeur() + courant2.getInfo().getValeur();
					// si <> 0 ajoute dans set la info et file temp
					if(newValeur != 0){
						Info info = new Info(newValeur,courant.getInfo().getColonne());
						courant.setInfo(info);
						file3.enfile(courant);
					}
	// defile les 2 files
					liste[i].defile();
					courant = liste[i].getPremier();
					matrice2.liste[i].defile();
					courant2 = matrice2.liste[i].getPremier();
				}else if(courant.getInfo().getColonne() < courant2.getInfo().getColonne()){
				}else{
					file3.enfile(courant2);
					courant2 = courant2.getSuivant();
				}

			}
			file3.concat(matrice2.liste[i]);
			file3.concat(this.liste[i]);
			// resultat
			this.liste[i] = file3;
			
		}
		
		
		return this;
	}


}
