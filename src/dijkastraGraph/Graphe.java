package dijkastraGraph;

public class Graphe {

	private File[] liste = null;
	private int[] predecesseur = null;
	private int[] pred = null;
	private int[] lambda = null;
	private int n = 0;
	private int col = 0;	// nombre de colonnes
	private final int _INFINITY_ = 999999999;

	public Graphe(int matrice[][])
	{

		graphe(matrice.length,matrice[0].length);

		for (int i=0 ; i<matrice.length ; i++){						
			for (int j=0 ; j<matrice[i].length; j++)
				liste[i].enfile(new Noeud(new Info(matrice[i][j])));
		}

	}

	public Graphe(int[][] matriceS, int[][] matriceL) {
		this.n = matriceS.length ;

		liste = new File[n] ;
		for (int i=0 ; i<n ; i++)
			liste[i] = new File() ;

		pred = new int[n] ;

		lambda = new int[n] ;

		for (int i=0 ; i<matriceS.length ; i++)	// boucle sur la matrice
			for (int j=0 ; j<matriceS[i].length; j++)
				liste[i].enfile(new Noeud(new Info(matriceS[i][j], matriceL[i][j])));

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

	public void afficheSuccEtDist() {
		for (int i=0 ; i<n ; i++)
		{
			System.out.print("ligne " + i + ": ");
			Noeud courant = liste[i].getPremier();
			while (courant != null)
			{
				System.out.print(courant.getInfo().getValeur()+ "/");
				System.out.print(courant.getInfo().getDistance() + "\t");
				courant = courant.getSuivant();
			}
			System.out.println();
		}
		System.out.println();

	}

	public void dijkstra() {
		boolean[] marques = new boolean[n];
		// initialisation du tableau lambda
		initLambdaMarques();

		// BOUCLER ICI (while  minVal = _INFINITY_ ??)
		int minVal = _INFINITY_; 
		int minNDX = 0; 		

		for(int k = 0; k < n; k++) {
			minVal = _INFINITY_;
			minNDX = 0;
			for (int i = 0; i < lambda.length; i++) {
				// si n'est pas marque
				if(marques[i] == false){
					// si la valeur du lambda est inferieur, on prend la valeur et l'index 
					if(lambda[i] < minVal){
						minVal = lambda[i];	// fixe la valeur
						minNDX = i;			// fixe l'index
					}
				}
			}
			// ici je reception mon minNDX (index lanbda)
			marques[minNDX] = true;
			Noeud courant = liste[minNDX].getPremier();
			while (courant != null)
			{
				int s = courant.getInfo().getValeur();
				int newDist = lambda[minNDX] + courant.getInfo().getDistance();
				lambda[s] = Math.min(lambda[s], newDist);

				courant = courant.getSuivant();
			}
		};

	}

	private void initLambdaMarques() {
		lambda[0] = 0;
		for (int i=1 ; i<n ; i++)
			lambda[i] = _INFINITY_;

	}

	public void afficheLambda() {
		// 
		for (int i=0 ; i<n ; i++)
			System.out.print(lambda[i]+"  ");

	}

	// Algorithme de Dijkstra 
	//-----------------------------------------------------------------------------
	// preconditions : valeurs >= 0 et tous les sommets atteignables
		
		public void dijkstra_CORRIGE()
	    {
			for (int i=0 ; i<n ; i++)  // initialisation
				lambda[i] = _INFINITY_;//MAX ;

			for (int i=0 ; i<n ; i++)
				pred[i] = -1 ;

			boolean definitif[] = new boolean[n] ;

			pred[0] = 0 ;
			lambda[0] = 0 ;

			for (int k=1 ; k<n ; k++)
	        {
				// recherche du sommet minimum
				int sommet = sommetMin(definitif) ;
				definitif[sommet] = true ;

				// ajustement des valeurs
				Noeud courant = liste[sommet].getPremier() ;

				while (courant != null)
	            {
					if (lambda[sommet] + courant.getInfo().getDistance() < lambda[courant.getInfo().getValeur()])
					{
						lambda[courant.getInfo().getValeur()]  = lambda[sommet] + courant.getInfo().getDistance();
						pred[courant.getInfo().getValeur()] = sommet ;
	                }

	            courant = courant.getSuivant() ;
	            }
	        }

	    }


		private int sommetMin(boolean[] definitif)
	    {
			int sommet = -1 ;
			int min = _INFINITY_;//MAX ;

			for (int i=0 ; i<n ; i++)
	        {
				if (!definitif[i] && lambda[i] < min)
	            {
					min = lambda[i] ;
					sommet = i ;
	            }
	        }

	    return sommet ;
	    }

}
