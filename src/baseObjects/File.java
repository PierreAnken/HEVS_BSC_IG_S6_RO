package baseObjects;

public class File {

	private int longueur ;
	private Noeud premier ;
	private Noeud dernier ;

	//constructor
	public File()
	{
		longueur = 0 ;
		premier = null ;
		dernier = null ;
	}

	// getters
	public int getLongueur()
	{
		return longueur ;
	}

	public Noeud getPremier()
	{
		return premier ;
	}

	public Noeud getDernier()
	{
		return dernier ;
	}

	// setters
	public void setLongueur(int longueur)
	{
		this.longueur = longueur ;
	}

	public void setPremier(Noeud premier)
	{
		this.premier = premier ;
	}

	public void setDernier(Noeud dernier)
	{
		this.dernier = dernier;
	}

	// methodes

	public boolean estVide()
	{
		return (longueur == 0) ;
	}

	public void enfile(Noeud nouveau)
	{
		if (estVide())
			premier = nouveau ;
		else
			dernier.setSuivant(nouveau) ;

		dernier = nouveau ;

		++longueur ;
	}

	public Noeud defile()
	{
		if (longueur == 0)
			return null ;

		Noeud debut = premier ;
		premier = premier.getSuivant() ;

		if (longueur == 1)
			dernier = null ;
		else
			debut.setSuivant(null) ;

		--longueur ;
		return debut ;
	}

	public Noeud defileElement(int n)
	{
		if (n<= 0 || n>longueur)
			return null ;

		File travail = new File() ;

		for (int i=1 ; i<n ; i++)
			travail.enfile(defile());

		Noeud retour = defile() ;

		travail.concat(this);
		concat(travail) ;
		return retour ;
	}


	public void concat(File file2)
	{
		if (file2.estVide())
			return ;

		if (estVide())
			premier = file2.getPremier();
		else
			dernier.setSuivant(file2.getPremier()) ;

		dernier = file2.getDernier() ;
		longueur += file2.getLongueur() ;

		file2.setLongueur(0);
		file2.setPremier(null);
		file2.setDernier(null);
	}
	public File fusionneFileTriee(File file2){
		// fusionne files triees
		// CONDITIONS : il faut que :
		// - la file elle-meme soit triee
		// - la file2 passee en parametres soit triee

		File file3 = new File();

		Noeud courant = premier ;
		Noeud courant2 = file2.getPremier() ;

		while (!estVide() && !file2.estVide())
		{

			if(courant.getInfo().getValeur()<courant2.getInfo().getValeur()){
				file3.enfile(courant);
				defile();
				courant = premier;
			}else{
				file3.enfile(courant2);
				file2.defile();
				courant2 = file2.getPremier() ;
			}

		}
		file3.concat(file2);
		file3.concat(this);

		return file3;

	}

	// echange de 2 noeuds references dans une file:
	// on echange les references des infos.
	public void echange(Noeud noeud1, Noeud noeud2)
	{
		Info temp = noeud1.getInfo() ;
		noeud1.setInfo(noeud2.getInfo());
		noeud2.setInfo(temp);
	}

	public void affiche()
	{
		Noeud courant = premier ;

		while (courant != null)
		{
			
			System.out.print(courant.getInfo().getValeur()+ " ");
			courant = courant.getSuivant() ;
		}
		System.out.println();
	}

	@Override
	public String toString()
	{
		String chaine = "" ;
		Noeud courant = premier ;

		while (courant != null)
		{
			chaine += courant.getInfo().toString() + " ";
			courant = courant.getSuivant() ;
		}
		return chaine ;
	}
	public Noeud rechercheSupprime(int x){
		// methode qui etant donne un entier x et une file, 
		// recherche et supprime le premier neud contenant la valeur x. 
		
		// rechercher le noeud avec valeur x
		int n = rechercheValeur(x);
		System.out.println("trouvee "+n);
		// defile le noeud avec l'element ne  
		return defileElement(n);
	}

	public int rechercheValeur(int x) {
		// renvoi le ne du 1er noeud avec la valeur x
		
		Noeud courant = premier;
		int n = this.longueur;
		for (int i=1 ; i<=n ; i++){
			// si on trouve la valeur x on returne l'index (ne element, noeud)
//			System.out.println(""+i+": courant.getInfo().getValeur():  "+courant.getInfo().getValeur());
			if (courant.getInfo().getValeur() == x) 
				return i;

			courant = courant.getSuivant();
		}

		// pas trouve
		return 0;
	}

	public static File radixSort(File travail){

		File files[] = new File[10];
		for (int i = 0; i < 10; i++) {
			files[i] = new File();
		}
		// pour chaque unité
		for (int exp = 0; exp < 2; exp++) {
			while(!travail.estVide()){
				int courant = travail.getPremier().getInfo().getValeur();
				//System.out.println("courant="+courant+"; exp="+exp+"; Math.pow(10, exp)="+(Math.pow(10, exp)));
				courant = (int) (courant/(Math.pow(10, exp))%10);
				files[courant].enfile(travail.defile());
			}
			
			for (int i = 0; i < 10; i++) {
				travail.concat(files[i]);
			}
		}
		return travail;
	}
	
	public void radixSort(){
		// this: pour trier la file en cours elle-meme
		radixSort(this);
	}
	
	
	public void fusionne(File file){

		// fusionne toutes les files, meme pas triees
		// concatene la file e elle meme 
		concat(file);
		// trie elle-meme
		radixSort();
	}
	
	public static File qSortFusion(File f1, File f2){

        File f3 = new File();
        while(!f1.estVide()&& !f2.estVide()){
        	
        	//on ajoute la plus petite valeur suivante à la file
             int val1 = f1.getPremier().getInfo().getValeur();
             int val2 = f2.getPremier().getInfo().getValeur();
             if (val1 < val2)
                  f3.enfile(f1.defile());
             else
                  f3.enfile(f2.defile());

        }
        f3.concat(f1);
        f3.concat(f2);
        return f3;
    }

	public void inverse() {
		//CC 24.05.2019
		
		Noeud n = defile();
		
		if(n == null)
			return;
		
		inverse();
		enfile(n);
		
	}

	public void incremente() {
		// Module 28.06.2019
		
		System.out.println("Increment de 1");
		
		//on inverse la file pour commencer par la fin
		inverse();
		
		boolean report = false; //stock le report d'unité=> dizaine
		Noeud courant = getPremier();
		
		do{
			//fin de file
			if(courant == null) {
				Noeud nouveau = new Noeud(new Info(1));
				enfile(nouveau);
				break;
			}
			
			//report?
			if(courant.getInfo().getValeur() == 0) {
				courant.getInfo().setValeur(1);
				report = false;
			}
			else {
				courant.getInfo().setValeur(0);
				report = true;
			}
			
			courant = courant.getSuivant();
			
		}while(report);
		
		inverse();
	}
}