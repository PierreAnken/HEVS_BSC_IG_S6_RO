package radixSort;

public class File {

	private int longueur ;
	private Noeud premier ;
	private Noeud dernier ;

	//constructeur
	public File()
	{
		longueur = 0 ;
		premier = null ;
		dernier = null ;
	}

	// getteurs
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

	// setteurs
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

	// Echange de 2 noeuds references dans une file:
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
		// defile le noeud avec l'element n
		return defileElement(n);
	}

	public int rechercheValeur(int x) {
		// renvoi le no du 1er noeud avec la valeur x

		Noeud courant = premier;
		int n = this.longueur;
		for (int i=1 ; i<=n ; i++){
			// si on trouve la valeur x on returne l'index (no element, noeud)
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

		// on execute 10 fois pour unites, dizaines, et les centaines et...
		for (int exp = 0; exp < 10; exp++) {
			while(!travail.estVide()){
				int courant = travail.getPremier().getInfo().getValeur();

				courant = (int) (courant/(Math.pow(10, exp))%10);
				files[courant].enfile(travail.defile());
			}
			for (int i = 0; i < 10; i++) {
				travail.concat(files[i]);
			}
		}
		return travail;
	}

	public void radixSort() {

		File file[] = new File[10] ;

		for (int i=0 ; i<10 ; i++) {
			file[i] = new File() ;
		}

		int diviseur = 1 ;

		for (int pos=0 ; pos<3 ; pos++) { // jusqu'à  999
			System.out.print("Début de l'étape : "+ pos);
			affiche();

			while (!estVide()) {
				int chiffre = premier.getInfo().getValeur()/diviseur %10 ;
				file[chiffre].enfile(defile()) ;
			}
		}


		for (int i=0 ; i<10 ; i++) {
			concat(file[i]) ; 
		}

		diviseur*=10 ;

	}

	//	public void radixSort(){
	//		// this: pour trier la file en cours elle-meme
	//		radixSort(this);
	//	}
	public void fusionne(File file){
		// fusionne toutes les files, meme pas triees
		// concatene la file e elle meme 
		concat(file);
		// trie elle-meme
		radixSort();
	}
}