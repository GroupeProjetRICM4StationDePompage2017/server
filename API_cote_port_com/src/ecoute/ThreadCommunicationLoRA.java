package ecoute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import bdd.SQL;
import ordre.Ordre;

public class ThreadCommunicationLoRA extends Thread 
{
	private static final int TAILLE_TRAME_DATA = 3;
	private static final int TAILLE_TRAME_ORDRE = 3;
	
	private static final int BEGIN_ETAT_POMPE = 7;
	private static final int BEGIN_NOMBRE_DE_NIVEAU = 6;
	private static final int BEGIN_NIVEAU = 1;
	private static final int BEGIN_ETAT_BATERIE = 3;
	
	private static final int OFFSET_ETAT_POMPE = 1;
	private static final int OFFSET_NOMBRE_DE_NIVEAU = 5;
	private static final int OFFSET_NIVEAU = 5;
	private static final int OFFSET_ETAT_BATERIE = 4;
	
	private SQL gestionaire_de_requetes;
	private InputStream flux_d_entree;
	private OutputStream flux_de_sortie;
	private BufferedReader buffer_de_sortie;
	
	public ThreadCommunicationLoRA(SQL gestionaire_de_requetes,InputStream flux_d_entree, OutputStream flux_de_sortie) {
		this.gestionaire_de_requetes = gestionaire_de_requetes;
		this.flux_d_entree = flux_d_entree;
		this.flux_de_sortie = flux_de_sortie;
		try 
		{
			this.buffer_de_sortie = new BufferedReader(new InputStreamReader(this.flux_d_entree, "US-ASCII"));
		} 
		catch (UnsupportedEncodingException e) 
		{
			System.out.println("Probleme ouverture du flux de lecture");
			e.printStackTrace();
		}	
	}

	public void envoyerOrdre(Ordre o)
	{
		
	}
	
	public void lireDonnee()
	{
		byte[] valeurs_lues = new byte[TAILLE_TRAME_DATA];
		
		try 
		{
			this.flux_d_entree.read(valeurs_lues);
		} catch (IOException e) {e.printStackTrace();}
		
		int id = valeurs_lues[0];
		
		int levelMax = (valeurs_lues[1]>>2) & 0x1F;
		
		int state = (valeurs_lues[1]>>7) & 0x1F;
		
		int level = ((valeurs_lues[1] & 0x3)<<3)|((valeurs_lues[2]>>5)& 0x7);
		
		int levelb = (valeurs_lues[2] & 0xB);
		//public void setData(int id,int level,int state,int levelb)
		
		
		
	}
	
	public static void printOrdre()
	{
		byte[] valeurs_lues = new byte[TAILLE_TRAME_DATA];
		
		valeurs_lues[0]=1;
		valeurs_lues[1]= new Byte("A");
		valeurs_lues[2]= 10;
		
		int id = valeurs_lues[0];
		
		int levelMax = (valeurs_lues[1]>>2) & 0x1F;
		
		int state = (valeurs_lues[1]>>7) & 0x1F;
		
		int level = ((valeurs_lues[1] & 0x3)<<3)|((valeurs_lues[2]>>5)& 0x7);
		
		int levelb = (valeurs_lues[2] & 0xB);
		
		System.out.println(id+"-"+state+"-"+levelMax+"-"+level+"-"+levelb);
	}

	public void run()
	{
		
	}
}
