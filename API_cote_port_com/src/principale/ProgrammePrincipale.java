package principale;

import ecoute.ThreadCommunicationLoRA;


public class ProgrammePrincipale {

	public static void main(String[] args) {
        //SQL coSQL = new SQL();
        //Ordre o = coSQL.getOrder();
        
        //Random rand = new Random();
        //coSQL.setData(1,rand.nextInt(9),rand.nextInt(2),rand.nextInt(11));
        //coSQL.fermutureConnexion();
		
		ThreadCommunicationLoRA.printOrdre();

	}

}
