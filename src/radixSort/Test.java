package radixSort;
public class Test {
	public static void main(String[] args) {
		File travail = new File();
		travail.enfile(new Noeud( new Info(123)));
		travail.enfile(new Noeud( new Info(13347)));
		travail.enfile(new Noeud( new Info(243)));
		travail.enfile(new Noeud( new Info(15232)));
		travail.enfile(new Noeud( new Info(861)));
		System.out.println("--------- travail-----------");
		travail.affiche();

		File travail2 = new File();
		travail2.enfile(new Noeud( new Info(52)));
		travail2.enfile(new Noeud( new Info(3357)));
		travail2.enfile(new Noeud( new Info(23)));
		travail2.enfile(new Noeud( new Info(1737)));
		travail2.enfile(new Noeud( new Info(59)));
		System.out.println("--------- travail2-----------");
		travail2.affiche();
		
		System.out.println("--------- fusionne travail1 et travail2-----------");
		travail.fusionne(travail2);
		travail.affiche();

		System.out.println("--------- recherche et supprime la valeur 861 -----------");
		travail.rechercheSupprime(861);
		travail.affiche();

	}

	
}
