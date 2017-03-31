package ecoute;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import ordre.Ordre;
import bdd.BDD;
import bdd.SQL;

public class ThreadCommunicationLoRA  extends Thread implements SerialPortEventListener  {
	private BDD gestionaire_de_requetes;
	private SerialPortConnexion port;
	private boolean running;
	
	public ThreadCommunicationLoRA(String  nom, BDD bdd){
		this.gestionaire_de_requetes = bdd;
		this.port = new SerialPortConnexion(nom);
		this.running=false;
	}

	public void envoyerOrdre(Ordre o, int id)
	{
		byte[] valeurs_envoie = translator.ordreToBytes(o);		
		
		this.port.write2(valeurs_envoie);		
		if(o!=null)
		{   System.out.println("ORDRE :"+o.toString());
			this.gestionaire_de_requetes.updateOrdre(id);
		}
		else{System.out.println("Pas d'ordre pour cette device");}
	}
	
	public Data lireDonnee()
	{
		byte[] valeurs_lues = this.port.read();
		if(valeurs_lues==null) return null;
		Data d = translator.bytesToData(valeurs_lues);
		System.out.println("DATA : "+d.toString());
		this.gestionaire_de_requetes.setData(d);
		return d;
	}
	
	
	public void run()
	{
		this.running=true;
		this.gestionaire_de_requetes.connexion();
		this.port.ouvrirPort();
		this.port.listener(this);
		while(this.running)
		{
			try {sleep(1);} catch (InterruptedException e) 
			{
				this.stopThread();
			}
		}
	}
	
	public void stopThread()
	{
		this.running=false;
		this.port.fermerPort();
		this.gestionaire_de_requetes.fermutureConnexion();
	}

	@Override
	public void serialEvent(SerialPortEvent arg0) {
		if(arg0.getEventType()==SerialPortEvent.RXCHAR)
		{
			System.out.println(">Debut action");
			Data read = this.lireDonnee();
			if(read!=null)
			{
				Ordre o = this.gestionaire_de_requetes.getOrder(read.getIdDevice());
				System.out.println("===>Envoie de l'ordre : ");
				try {sleep(10000);} catch (InterruptedException e) {e.printStackTrace();}
				this.envoyerOrdre(o,read.getIdDevice());
			}
			System.out.println("fin action");
		}
		
	}
}
