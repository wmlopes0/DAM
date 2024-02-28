
public class Puente {
	int numPeatones;
	boolean barco; // Hay un barco o el barco quiera pasar!
	
	
	
	public  void pasaPeaton(String nombrePeaton){
		synchronized (this) {
			while(barco){
				try {	wait();	} catch (InterruptedException e) {e.printStackTrace();}
			}
			numPeatones++;
			System.out.println("Entra "+ nombrePeaton + " por el puente. Hay "+numPeatones);
		}
        //zona no sincronizada, puede haber varios peatones en el puente
		System.out.println("\t"+nombrePeaton+" está en el puente");
		
		synchronized (this) {
			
			numPeatones--;
			System.out.println("\t\t=>Sale "+ nombrePeaton+" del puente. Quedan "+numPeatones);
			if( numPeatones == 0){
				notifyAll();
			}
		}
	}
	
	
	public  void pasaBarco(String nombreBarco){
		synchronized (this) {
			//Siempre pasa:
			barco = true;
			System.out.println("*************** BARCO QUIERE PASAR ****************");
			while(numPeatones > 0){
				
				try {	wait();	} catch (InterruptedException e) {e.printStackTrace();}
			}
			
		}
		
		System.out.println("\t"+ nombreBarco + " está cruzando....<__P__>");
		
		synchronized (this) {
			System.out.println("\t\tEl barco "+nombreBarco+ " salió del puente.");
			barco = false;
			notifyAll();
		}
		
		
		
		
		
		
	}
	
	
	
}
