package baseObjects;

import baseObjects.File;
import baseObjects.Info;
import baseObjects.Noeud;

public class Graphe {
		
	private File[] liste = null;
	private int[] predecesseur = null;
	private int nbrSommet = 0;
	private int col = 0;	// nombre de colonnes
	private int[] pred = null;
	private int[] lambda = null;
	private final int _INFINITY_ = 999999999;
	private int arcs = 0;
	

	public Graphe(int matrice[][])
	{
	
		this.nbrSommet =  matrice.length;
		this.predecesseur = new int[nbrSommet];
		
		this.liste = new File [nbrSommet];
		for (int i = 0; i < nbrSommet; i++) 
			liste[i] = new File();
		
		for (int i=0 ; i<matrice.length ; i++){						
			for (int j=0 ; j<matrice[i].length; j++)
				liste[i].enfile(new Noeud(new Info(matrice[i][j])));
		}
	}	
		
	public Graphe(int[][] matriceS, int[][] matriceL) {
		this.nbrSommet = matriceS.length ;
	
		liste = new File[nbrSommet] ;
		for (int i=0 ; i<nbrSommet ; i++)
			liste[i] = new File() ;
		
		pred = new int[nbrSommet] ;
	
		lambda = new int[nbrSommet] ;
	
		for (int i=0 ; i<matriceS.length ; i++)	// boucle sur la matrice
			for (int j=0 ; j<matriceS[i].length; j++)
				liste[i].enfile(new Noeud(new Info(matriceS[i][j], matriceL[i][j])));

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
		boolean[] marques = new boolean[this.nbrSommet];
		this.predecesseur = new int[nbrSommet];
		int s = valeur;
		File F = new File();
		// depuis le sommet
		F.enfile(new Noeud(new Info(valeur)));
		
		while (!F.estVide()){	
			// tant que F est non vide
			s = F.defile().getInfo().getValeur();	// le sortir de F
			marques[s] = true;							// marquer s
			//System.out.println("s = "+s);
			
			//afficheFiles();
			
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
			//System.out.println("marques["+i+"] = "+marques[i]+" : predecesseur["+i+"] = "+predecesseur[i]);
		}
		
		return marques;
	}
	
	public void afficheChemin(int depart, int arrivee){
		boolean[] destinations = exploreFile(depart);
		if (destinations[arrivee] == false){
			System.out.println("Solution impossible: aucune destination");
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
	
	public void afficheSuccEtDist() {
		for (int i=0 ; i<nbrSommet ; i++)
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

	public void couplageSimple() {
		
		boolean[] couplages = new boolean[this.nbrSommet];
		
		//pour chaque employé
		for (int i = 0; i < liste.length; i++) {
			
			Noeud n = liste[i].defile();
			File couplagesM = new File();
			
			//pour chaque machine utilisable
			while(n != null ) {
				
				//si machine libre et employé sans machine
				if(!couplages[n.getInfo().getValeur()] && couplagesM.estVide()) {
					
					//on la marque
					couplages[n.getInfo().getValeur()] = true;
					
					//on la garde
					couplagesM.enfile(n);
				}
				
				n = liste[i].defile();
			}
			
			//on sauvegarde la machine attribuée
			liste[i].concat(couplagesM);
		
		}
	}
	
	public void explorationSimple(int depart) {
		explorationSimple(depart,this);
	}
	public static void explorationSimple(int depart, Graphe graphe)
	{
	    boolean[] marque = new boolean[graphe.nbrSommet] ;
	
	    for (int i=0 ; i<graphe.nbrSommet ; i++)  // initialisation
	         marque[i] = false ;
	    
	    File file = new File();
	    file.enfile(new Noeud(new Info(depart))) ;  // On met depart dans la file
	
	    while (!file.estVide())                         // F non vide
	    {
	         int sommet = file.defile().getInfo().getValeur();  // s un élément de F on le sort
	         marque[sommet] = true ;                      //  marquage du premier
	         System.out.println("marquage de: " + sommet);
	
	         Noeud courant = graphe.liste[sommet].getPremier() ;
	         while (courant!= null)
	        {
	        int successeur = courant.getInfo().getValeur();
	         if (marque[successeur] == false)   // non marqué
	            {
	              if (file.rechercheValeur(successeur) == 0)
	                  file.enfile(new Noeud(new Info(courant.getInfo().getValeur()))) ;
	            }
	            courant = courant.getSuivant() ;
	        }
	     }
	    
	}
	
	public void afficheLambda() {
		// 
		for (int i=0 ; i<nbrSommet ; i++)
			System.out.print(lambda[i]+"  ");

	}

	// Algorithme de Dijkstra 
	//-----------------------------------------------------------------------------
	// preconditions : valeurs >= 0 et tous les sommets atteignables
		
		public void dijkstra_CORRIGE()
	    {
			for (int i=0 ; i<nbrSommet ; i++)  // initialisation
				lambda[i] = _INFINITY_;//MAX ;

			for (int i=0 ; i<nbrSommet ; i++)
				pred[i] = -1 ;

			boolean definitif[] = new boolean[nbrSommet] ;

			pred[0] = 0 ;
			lambda[0] = 0 ;

			for (int k=1 ; k<nbrSommet ; k++)
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

			for (int i=0 ; i<nbrSommet ; i++)
	        {
				if (!definitif[i] && lambda[i] < min)
	            {
					min = lambda[i] ;
					sommet = i ;
	            }
	        }

	    return sommet ;
	    }

		public void getArcs() {
			arcs = 0;
			for(File file : liste) {
				arcs+=file.getLongueur();
			}
			System.out.println("Nombre d'arcs: "+arcs);
		}

		public void convertBidirectionnel() {
			
			//pour chaque sommet
			for(int sommet = 0; sommet <liste.length; sommet++ ) {		
				//pour chaque arc
				Noeud n = liste[sommet].getPremier();
				while(n != null) {
					//si un sens inverse n'existe pas
					if(liste[n.getInfo().getValeur()].rechercheValeur(sommet) == 0)
						//On le crée
						liste[n.getInfo().getValeur()].enfile(new Noeud(new Info(sommet)));
					
					n = n.getSuivant();
				}	
			}
			//on trie les successeurs pour faire joli
			trieSuccesseursSommets();
			System.out.println("Graphe convertit en bidirectionnel");
		}
		
		public void trieSuccesseursSommets() {
			for(File sommet : liste)
			{
				sommet.radixSort();
			}
		}

		public void affichePlusPetiteArrete() {
			//CC 24.05.2019
			int distanceMin = _INFINITY_;
			int sommetMin = -1;
			int sommetDestMin = -1;
			
			//pour chaque sommet de la liste
			for(int sommet = 0; sommet < liste.length; sommet++) {
				
				//pour chaque successeur du sommet
				Noeud courant = liste[sommet].getPremier() ;
				while (courant != null)
	            {
					//si la distance est plus petite que la valeur enregistrée
					if(courant.getInfo().getDistance()<distanceMin) {
						distanceMin = courant.getInfo().getDistance();
						sommetMin =  sommet;
						sommetDestMin = courant.getInfo().getValeur();
					}
	            	courant = courant.getSuivant() ;
	            }
			}
			System.out.println("La plus petite arête du graphe est l'arête ("+sommetMin+", "+sommetDestMin+") de valeur "+distanceMin);
		}

		public boolean aUnChemin(int depart, int arrivee){
			//CC 24.05.2019
			
			boolean[] destinations = exploreFile(depart);
			if (destinations[arrivee] == false){
				return false;
			}
			File fileResultat = new File();
			int enCours = arrivee;
			while (enCours != depart){
				fileResultat.enfile(new Noeud(new Info(enCours)));
				enCours = predecesseur[enCours];
			}
			fileResultat.enfile(new Noeud(new Info(depart)));
			return true;
		}
		
		public void fortementConnexe(int sommetA, int sommetB) {
			//CC 24.05.2019
			
			// 2 sommets sont fortement connexes s'il existe un chemin aller-retour entre les 2
			boolean fortementConnexe = false;
			boolean cheminAller = aUnChemin(sommetA,sommetB);
			
			if(cheminAller)
				fortementConnexe = aUnChemin(sommetB,sommetA);
			
			System.out.println("Est-ce que les sommets "+sommetA+" et "+sommetB+" sont dans la même composante fortement connexe? "+fortementConnexe);
		
		}
}

