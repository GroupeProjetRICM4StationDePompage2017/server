package ecoute;

import ordre.Ordre;

public class translator {
	
	public static final int TAILLE_TRAME_DATA = 3;
	private static final int TAILLE_TRAME_ORDRE = 3;
	private static final int OCTET_ID = 0;
	private static final int OCTET_ETAT_POMPE = 1;
	private static final int OCTET_NOMBRE_DE_NIVEAU = 1;
	private static final int OCTET_NIVEAU_P1 = 1;
	private static final int OCTET_NIVEAU_P2 = 2;
	private static final int OCTET_ETAT_BATERIE = 2;
	
	private static final int OCTET_ORDRE_ID = 0;
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
	
	public static byte[] ordreToBytes(Ordre o)
	{
		byte[] valeurs_envoie = new byte[TAILLE_TRAME_ORDRE];
		if(o == null)
		{
			valeurs_envoie[OCTET_ORDRE_ID] = 0;
			valeurs_envoie[OCTET_ORDRE_IDR] = 0;
			valeurs_envoie[OCTET_ORDRE_NIVEAU] = 0;
		}
		else
		{
			valeurs_envoie[OCTET_ORDRE_ID] = (byte) new Byte(o.getIdDevice());
			valeurs_envoie[OCTET_ORDRE_IDR] = (byte) 0x55;
			valeurs_envoie[OCTET_ORDRE_NIVEAU] = (byte) ((o.getLevel_require()<<OFFSET_NIVEAU_ORDRE)|MASK_ORDRE);
		}
		return valeurs_envoie;
	}
	
	public static Data bytesToData(byte[] b)
	{
		
		int id = (byte) b[OCTET_ID];
		int state = (byte) (b[OCTET_ETAT_POMPE]>>OFFSET_ETAT_POMPE) & MASK_ETAT_POMPE;
		int levelMax = (byte) (b[OCTET_NOMBRE_DE_NIVEAU]>>OFFSET_NOMBRE_DE_NIVEAU) & MASK_NOMBRE_DE_NIVEAU;
		int level = (byte) ((b[OCTET_NIVEAU_P1] & MASK_NIVEAU_P1)<<OFFSET_NIVEAU_P1)|((b[OCTET_NIVEAU_P2]>>OFFSET_NIVEAU_P2)& MASK_NIVEAU_P2);
		int levelb = (byte) (b[OCTET_ETAT_BATERIE] & MASK_ETAT_BATERIE);
		
		Data donnees = new Data(id,state,levelMax,level,levelb);
		return donnees;
	}

}
