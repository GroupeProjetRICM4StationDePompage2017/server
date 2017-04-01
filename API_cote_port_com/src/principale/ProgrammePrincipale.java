package principale;

import java.util.Random;

import ordre.Ordre;
import bdd.PHP;
import bdd.SQL;
import ecoute.ThreadCommunicationLoRA;
import ecoute.ThreadCommunicationLoRASQL;

//http://javadox.com/org.scream3r/jssc/2.8.0/javadoc/jssc/package-summary.html
/**
 * @author Héloïse
 *
 */
public class ProgrammePrincipale {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			
			if(args.length>=1)
			{
				//ThreadCommunicationLoRASQL lora = new ThreadCommunicationLoRASQL(args[0]);
				ThreadCommunicationLoRA lora = new ThreadCommunicationLoRA(args[0],new PHP());
				lora.start();
				lora.join();
				lora.stopThread();
			}
			else{System.out.println("Vous n'avez pas precise le port");}
		} catch (InterruptedException e) {e.printStackTrace();}
		
		

	}

}
