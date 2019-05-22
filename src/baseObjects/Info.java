package baseObjects;


public class Info {
	private int valeur;
	private int distance;
	private int colonne;
	
	public Info(int valeur, int distance) 
	{
		this.valeur = valeur ;	
		this.distance = distance ;	
	}

	public Info(int valeur) {
		this.valeur = valeur ;	
		this.distance = 0 ;	
	}

	public int getValeur()
	{
		return valeur ;
	}
	
	public int getDistance()
	{
		return distance ;
	}
	
	public String toString()
	{
		return (valeur  + "/" + distance + " , ") ;
	}

	public int getColonne() {
		return colonne;
	}
}