package chainedList;

public class Main {

	public static void main(String[] args) {
		
		File file1 = new File();
		
		Noeud n1 = new Noeud(23);
		Noeud n2 = new Noeud(123);
		Noeud n3 = new Noeud(283);
		Noeud n4 = new Noeud(453);
		
		file1.enfile(n1);
		file1.enfile(n2);
		file1.enfile(n3);
		file1.enfile(n4);
		
		file1.affiche();
		
		file1.removeFromValue(283);
		
		file1.affiche();
	}

}
