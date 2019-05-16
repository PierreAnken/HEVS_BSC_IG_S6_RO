package graph;

public class Graphe {
	
	private File[] liste = null;
	private int[] predecesseur = null;
	private int n = 0;
	private int col = 0;	// nombre de colonnes

	public Graphe(int matrice[][])
	{
	
		graphe(matrice.length,matrice[0].length);
		
		for (int i=0 ; i<matrice.length ; i++){						
			for (int j=0 ; j<matrice[i].length; j++)
				liste[i].enfile(new Noeud(new Info(matrice[i][j])));
	}

}

	private void graphe(int n, int col){
		this.n = n;
		this.col = col;
		this.predecesseur = new int[n];
		
		this.liste = new File [n];
		for (int i = 0; i < n; i++) 
			liste[i] = new File();
	}
	public boolean[] exploreFile (int valeur){
		/*
		F = {a}
		tant que F est non vide
		soit  s un element de F
			marquer s
			le sortir de F
			introduire tous les successeurs de s non marques dans F
		 */
		boolean[] marques = new boolean[this.n];
		this.predecesseur = new int[n];
		int s = valeur;
		File F = new File();
		// depuis le sommet
		F.enfile(new Noeud(new Info(valeur)));
		
		while (!F.estVide()){						// tant que F est non vide
			s = F.defile().getInfo().getValeur();	// le sortir de F
			marques[s] = true;							// marquer s
			System.out.println("s = "+s);afficheFiles();
			// introduire tous les successeurs de s non marques dans F
			Noeud courant = liste[s].getPremier();
			while (courant != null){
				int successeur = courant.getInfo().getValeur();
				// pas deje dans la file F et non marques dans F
				if(F.rechercheValeur(successeur) == 0 && marques[successeur] == false){
					F.enfile(courant);
					predecesseur[successeur] = s;
				}
				courant = courant.getSuivant();
			}
		}
		
		for (int i = 0; i < marques.length; i++) {
			System.out.println("marques["+i+"] = "+marques[i]+" : predecesseur["+i+"] = "+predecesseur[i]);
		}
		
		return marques;
	}
	public void chemin(int depart, int arrivee){
		boolean[] destinations = exploreFile(depart);
		if (destinations[arrivee] == false){
			System.out.println("Solution impossile: aucune destination");
			return;
		}
		File fileResultat = new File();
		int enCours = arrivee;
		while (enCours != depart){
			fileResultat.enfile(new Noeud(new Info(enCours)));
			enCours = predecesseur[enCours];
		}
		fileResultat.enfile(new Noeud(new Info(depart)));
		System.out.println("Chemin trouve ");
		fileResultat.affiche();
	}
	public void afficheFiles() {
		System.out.println("\n----- Affiche liste de files -----");
		for (int i = 0; i < liste.length; i++) {
			System.out.print("file "+i+": \t");
			//liste[i].affiche();
	
			Noeud courant = liste[i].getPremier() ;
	
			while (courant != null)
			{
				System.out.print(courant.getInfo().getValeur()+ " ");
				courant = courant.getSuivant() ;
			}
			System.out.println();	
		}
	}
		
	}
