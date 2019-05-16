package matriceCreuses;
public class testMain {

	public static void main(String[] args) {

		int m1[][] = { {0, 2, 0, 1}, 
					{4, 0, 3, 0},
					{1, 0, 0, 1}};
			
		int m2[][] = {{0, 0, 0, -1}, 
					{0, 0, 0, 4},
					{1, 6, 0, 0}};
		
		Matrice matrice1 = new Matrice(m1);
		Matrice matrice2 = new Matrice(m2);
		
		matrice1.afficheMatrice();
		matrice1.afficheFiles();
		matrice2.afficheMatrice();
		matrice2.afficheFiles();	
		
		Matrice matrice3 = matrice1.addition(matrice2);
		
		matrice3.afficheMatrice();
		matrice3.afficheFiles();

	}

}
