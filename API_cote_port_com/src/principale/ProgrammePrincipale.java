package principale;

import java.util.Random;

import ordre.Ordre;
import bdd.SQL;
import ecoute.ThreadCommunicationLoRA;

//http://javadox.com/org.scream3r/jssc/2.8.0/javadoc/jssc/package-summary.html
public class ProgrammePrincipale {

	public static void main(String[] args) {
        //SQL coSQL = new SQL();
        //coSQL.connexion();
        //Ordre o = coSQL.getOrder();
        //Random rand = new Random();
        //coSQL.setData(1,rand.nextInt(9),rand.nextInt(2),rand.nextInt(11));
        //coSQL.fermutureConnexion();
		//System.out.println("blopblop");
		
		
		try {
			ThreadCommunicationLoRA lora = new ThreadCommunicationLoRA("COM4");
			lora.start();
			lora.join();
			lora.stopThread();
		} catch (InterruptedException e) {e.printStackTrace();}
		
		

	}

}
