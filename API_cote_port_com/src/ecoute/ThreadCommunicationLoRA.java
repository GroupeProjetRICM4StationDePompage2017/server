package ecoute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import bdd.SQL;
import ordre.Ordre;

public class ThreadCommunicationLoRA extends Thread implements SerialPortEventListener
{
	
	private SQL gestionaire_de_requetes;
	private SerialPortConnexion port;
	
	public ThreadCommunicationLoRA(String  nom) {
		this.gestionaire_de_requetes = new SQL();
		this.port = new SerialPortConnexion(nom);
	}

	public void envoyerOrdre(Ordre o)
	{
		byte[] valeurs_envoie = translator.ordreToBytes(o);		
		this.port.write(valeurs_envoie);		
		System.out.println("Ordre :"+o.toString());
		//this.gestionaire_de_requetes.updateOrdre();
	}
	
	public void lireDonnee()
	{
		//byte[] valeurs_lues = new byte[translator.TAILLE_TRAME_DATA];
		
		System.out.println("je lit");
		byte[] valeurs_lues = valeurs_lues = this.port.read();
		Data d = translator.bytesToData(valeurs_lues);
		System.out.println("DATA : "+d.toString());
		this.gestionaire_de_requetes.setData(d.getIdDevice(), d.getLevel(), d.getState(), d.getLevelpower());
	}
	
	public void run()
	{
		this.gestionaire_de_requetes.connexion();
		this.port.ouvrirPort();
		this.port.listener(this);
		System.out.println("SQL + port ouvert");
		while(true)
		{
			
		}
		
	}
	
	public void stopThread()
	{
		this.port.fermerPort();
		this.gestionaire_de_requetes.fermutureConnexion();
	}

	@Override
	public void serialEvent(SerialPortEvent arg0) {
		System.out.println("Event");
		if(arg0.getEventType()==SerialPortEvent.RXCHAR)
		{
			System.out.println("Debut action");
			this.lireDonnee();
			Ordre o = this.gestionaire_de_requetes.getOrder();
			System.out.println("Send ordre");
			try {
				sleep(10000);} catch (InterruptedException e) {	e.printStackTrace();	}
			this.envoyerOrdre(o);
			System.out.println("fin action");
		}
		
	}
}
