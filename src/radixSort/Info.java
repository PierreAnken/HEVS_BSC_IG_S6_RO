package radixSort;

public class Info {

	private int valeur ;

	public Info(int valeur)
	{
		this.valeur = valeur ;
	}

	//getter
	public int getValeur()
	{
		return valeur ;
	}

	//setter
	public void setValeur(int valeur)
	{
		this.valeur = valeur ;
	}

	@Override
	public String toString()
	{
		return (valeur + " ") ;
	}
}