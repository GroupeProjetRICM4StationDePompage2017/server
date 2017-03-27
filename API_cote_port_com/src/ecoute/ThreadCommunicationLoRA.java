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
	private boolean running;
	
	public ThreadCommunicationLoRA(String  nom) {
		this.gestionaire_de_requetes = new SQL();
		this.port = new SerialPortConnexion(nom);
		this.running=false;
	}

	public void envoyerOrdre(Ordre o)
	{
		byte[] valeurs_envoie = translator.ordreToBytes(o);		
		this.port.write2(valeurs_envoie);		
		System.out.println("ORDRE :"+o.toString());
		//this.gestionaire_de_requetes.updateOrdre();
	}
	
	public boolean lireDonnee()
	{
		byte[] valeurs_lues = this.port.read();
		Data d = translator.bytesToData(valeurs_lues);
		//if(d==null){return false;}
		System.out.println("===>Lire");
		System.out.println("DATA : "+d.toString());
		this.gestionaire_de_requetes.setData(d.getIdDevice(), d.getLevel(), d.getState(), d.getLevelpower());
		return true;
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
			boolean read = this.lireDonnee();
			if(read)
			{
				Ordre o = this.gestionaire_de_requetes.getOrder();
				System.out.println("===>Envoie de l'ordre : ");
				try {sleep(10000);} catch (InterruptedException e) {e.printStackTrace();}
				this.envoyerOrdre(o);
				//this.lireDonnee2();
			}
			System.out.println("fin action");
		}
		
	}
}
