package chainedList;

public class Main {

	public static void main(String[] args) {
		
		File file1 = new File();

		file1.enfile(new Noeud(123));
		file1.enfile(new Noeud(41));
		file1.enfile(new Noeud(285));
		file1.enfile(new Noeud(453));
		file1.enfile(new Noeud(2));

		file1.affiche();
		
		file1 = RadixSort.sort(file1, 2);
		
		System.out.println("Etapes nécessaires au tri: "+RadixSort.countEtapes);
		
		file1.affiche();
	}

}
