package principale;

import java.util.Random;

import ordre.Ordre;
import bdd.SQL;
import ecoute.ThreadCommunicationLoRA;

//http://javadox.com/org.scream3r/jssc/2.8.0/javadoc/jssc/package-summary.html
public class ProgrammePrincipale {

	public static void main(String[] args) {

		try {
			
			if(args.length>=1)
			{
				ThreadCommunicationLoRA lora = new ThreadCommunicationLoRA(args[0]);
				lora.start();
				lora.join();
				lora.stopThread();
			}
			else{System.out.println("Vous n'avez pas precise le port");}
		} catch (InterruptedException e) {e.printStackTrace();}
		
		

	}

}
