package chainedList;

public class RadixSort {
	
	public static int countEtapes = 0;
	
	public static File sort(File file, int decimal) {
		
		File[] cashiers = new File[10];
		for(int i = 0; i<10; i++){
			cashiers[i] = new File();
		}
		
		File sortedFile = new File();
		
		//for each element of the file
		Noeud n = null;
		do{
			//we sort it in the right cashier
			n = file.defile();
			countEtapes++;
			if(n == null)
				break;
			
			int value = n.getInfo().getValeur();
			
			//get x digit from number
			int digit = 0;
			String nbrS = value+"";
			if(nbrS.length()> decimal) {
				digit = Integer.parseInt(nbrS.charAt(nbrS.length()-decimal-1)+"");
			}
			
			cashiers[digit].enfile(n);
			countEtapes++;
			
		}while(n != null);
		
		//we reconstruct the file
		
		//for each cashier
		for(int i = 0; i<10; i++){

			int longueur = cashiers[i].getLongueur();
			if(longueur > 0){
				
				if(decimal > 1) {
					//we sort the sub cashier
					cashiers[i] = RadixSort.sort(cashiers[i], decimal-1);
				}
				
				//for each element of the cashier
				do{
					n = cashiers[i].defile();
					countEtapes++;
					if(n == null)
						break;
					
					sortedFile.enfile(n);
					countEtapes++;
				}while(n != null);
			}
		}
		
		return sortedFile;
	}
}

