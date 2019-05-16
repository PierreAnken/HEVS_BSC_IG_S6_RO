package graph;

public class Info {
	private int valeur;
	private int colonne;
	
	public Info(int valeur, int colonne) 
	{
		this.valeur = valeur ;	
		this.colonne = colonne ;	
	}

	public Info(int valeur) {
		this.valeur = valeur ;	
		this.colonne = 0 ;	
	}

	public int getValeur()
	{
		return valeur ;
	}
	
	public int getColonne()
	{
		return colonne ;
	}
	
	public String toString()
	{
		return (valeur  + "/" + colonne + " , ") ;
	}
}