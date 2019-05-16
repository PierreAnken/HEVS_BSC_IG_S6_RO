package dijkastraGraph;
public class TestDijkstraBis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub


		int matrice1Suc[][] =  	{{1, 2, 3},
								{4},
								{1, 3, 5, 6},
								{2, 5, 6},
								{2, 7},
								{4, 6, 7},
								{7},
								{6}};
		
		int matrice1Dist[][] =  {{1, 5, 7},
								{3},
								{1, 3, 1, 9},
								{2, 2, 4},
								{0, 8},
								{1, 4, 7},
								{2},
								{1}};

		Graphe graphe = new Graphe(matrice1Suc,  matrice1Dist) ;
		graphe.afficheSuccEtDist();
		
		graphe.dijkstra() ;
		graphe.afficheLambda() ;

	}

}