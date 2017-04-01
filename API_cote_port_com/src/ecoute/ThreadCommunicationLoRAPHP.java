package ecoute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.util.Date;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import bdd.SQL;
import ordre.Ordre;
import jssc.SerialPortEvent;
import ordre.Ordre;
import bdd.PHP;

/**
 * @author Héloïse
 *
 */
public class ThreadCommunicationLoRAPHP extends Thread implements SerialPortEventListener {
	
	/**
	 * 
	 */
	private PHP gestionaire_de_requetes;
	/**
	 * 
	 */
	private SerialPortConnexion port;
	/**
	 * 
	 */
	private boolean running;
	
	/**
	 * @param nom
	 */
	public ThreadCommunicationLoRAPHP(String  nom) {
		this.gestionaire_de_requetes = new PHP();
		this.port = new SerialPortConnexion(nom);
		this.running=false;
	}

	/**
	 * @param o
	 * @param id
	 */
	public void envoyerOrdre(Ordre o, int id)
	{
		byte[] valeurs_envoie = Translator.ordreToBytes(o);		
		
		this.port.write2(valeurs_envoie);		
		if(o!=null)
		{   System.out.println("ORDRE :"+o.toString());
			this.gestionaire_de_requetes.updateOrdre(id);
		}
		else{System.out.println("Pas d'ordre pour cette device");}
	}
	
	/**
	 * @return
	 */
	public Data lireDonnee()
	{
		byte[] valeurs_lues = this.port.read();
		if(valeurs_lues==null) return null;
		Data d = Translator.bytesToData(valeurs_lues);
		System.out.println("DATA : "+d.toString());
		this.gestionaire_de_requetes.setData(d);
		return d;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run()
	{
		this.running=true;
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
	
	/**
	 * 
	 */
	public void stopThread()
	{
		this.running=false;
		this.port.fermerPort();
	}

	/* (non-Javadoc)
	 * @see jssc.SerialPortEventListener#serialEvent(jssc.SerialPortEvent)
	 */
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
