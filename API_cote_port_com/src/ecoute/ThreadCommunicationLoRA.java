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
	
	private static final int OCTET_ID = 0;
	private static final int OCTET_ETAT_POMPE = 1;
	private static final int OCTET_NOMBRE_DE_NIVEAU = 1;
	private static final int OCTET_NIVEAU_P1 = 1;
	private static final int OCTET_NIVEAU_P2 = 2;
	private static final int OCTET_ETAT_BATERIE = 2;
	
	private static final int OCTET_ODRE_ID = 0;
	private static final int OCTET_ORDRE_IDR = 1;
	private static final int OCTET_ORDRE_NIVEAU = 2;
	
	private static final int MASK_ETAT_POMPE = 0x1;
	private static final int MASK_NOMBRE_DE_NIVEAU = 0x1F;
	private static final int MASK_NIVEAU_P1 = 0x3;
	private static final int MASK_NIVEAU_P2 = 0x7;
	private static final int MASK_ETAT_BATERIE = 0xF;
	
	private static final int MASK_ORDRE = 4;
	
	private static final int OFFSET_ETAT_POMPE =7;
	private static final int OFFSET_NOMBRE_DE_NIVEAU = 2;
	private static final int OFFSET_NIVEAU_P1 = 3;
	private static final int OFFSET_NIVEAU_P2 = 5;
	private static final int OFFSET_NIVEAU_ORDRE = 3;
	
	private SQL gestionaire_de_requetes;
	private InputStream flux_d_entree;
	private OutputStream flux_de_sortie;
	private BufferedReader buffer_de_sortie;
	
	public ThreadCommunicationLoRA(InputStream flux_d_entree, OutputStream flux_de_sortie) {
		this.gestionaire_de_requetes = new SQL();
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
		
		byte[] valeurs_envoie = new byte[TAILLE_TRAME_ORDRE];
		if(o == null)
		{
			valeurs_envoie[OCTET_ODRE_ID] = 0;
			valeurs_envoie[OCTET_ORDRE_IDR] = 0;
			valeurs_envoie[OCTET_ORDRE_NIVEAU] = 0;
		}
		else
		{
			valeurs_envoie[OCTET_ODRE_ID] = new Byte(o.getIdDevice());
			valeurs_envoie[OCTET_ORDRE_IDR] = 0;
			valeurs_envoie[OCTET_ORDRE_NIVEAU] = (byte) ((o.getLevel_require()<<OFFSET_NIVEAU_ORDRE)|MASK_ORDRE);
		}
		
		try {
			this.flux_de_sortie.write(valeurs_envoie);
		} catch (IOException e) {e.printStackTrace();}
		
	}
	
	public void lireDonnee()
	{
		byte[] valeurs_lues = new byte[TAILLE_TRAME_DATA];
		
		try 
		{
			this.flux_d_entree.read(valeurs_lues);
		} catch (IOException e) {e.printStackTrace();}
		
		int id = valeurs_lues[OCTET_ID];
		int state = (valeurs_lues[OCTET_ETAT_POMPE]>>OFFSET_ETAT_POMPE) & MASK_ETAT_POMPE;
		int levelMax = (valeurs_lues[OCTET_NOMBRE_DE_NIVEAU]>>OFFSET_NOMBRE_DE_NIVEAU) & MASK_NOMBRE_DE_NIVEAU;
		int level = ((valeurs_lues[OCTET_NIVEAU_P1] & MASK_NIVEAU_P1)<<OFFSET_NIVEAU_P1)|((valeurs_lues[OCTET_NIVEAU_P2]>>OFFSET_NIVEAU_P2)& MASK_NIVEAU_P2);
		int levelb = (valeurs_lues[OCTET_ETAT_BATERIE] & MASK_ETAT_BATERIE);
		
		this.gestionaire_de_requetes.setData(id, level, state, levelb);
	}
	
	public void run()
	{
		while(true)
		{
			this.lireDonnee();
			Ordre o = this.gestionaire_de_requetes.getOrder();
			this.envoyerOrdre(o);
		}
		
	}
}
