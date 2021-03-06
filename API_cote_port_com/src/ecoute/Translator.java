package ecoute;

import org.json.JSONException;
import org.json.JSONObject;

import ordre.Ordre;

/**
 * @author H�lo�se
 *
 */
public class Translator {
	
	/**
	 * Taille des trames
	 * */
	public static final int TAILLE_TRAME_DATA = 4;
	private static final int TAILLE_TRAME_ORDRE = 3;
	
	/**
	 * Numero de l'octet ou se trouve la donnee
	 * */
	//DATA
	private static final int OCTET_ID = 1;
	private static final int OCTET_ETAT_POMPE = 2;
	private static final int OCTET_NOMBRE_DE_NIVEAU = 2;
	private static final int OCTET_NIVEAU_P1 = 2;
	private static final int OCTET_NIVEAU_P2 = 3;
	private static final int OCTET_ETAT_BATERIE = 3;
	
	//ORDRE
	private static final int OCTET_ORDRE_ID = 0;
	private static final int OCTET_ORDRE_IDR = 1;
	private static final int OCTET_ORDRE_NIVEAU = 2;
	private static final int MASK_ETAT_POMPE = 0x1;
	
	/**
	 * MASK a appliquer sur l'octet pour recuperer la donne
	 */
	private static final int MASK_NOMBRE_DE_NIVEAU = 0x1F;
	private static final int MASK_NIVEAU_P1 = 0x3;
	private static final int MASK_NIVEAU_P2 = 0x7;
	private static final int MASK_ETAT_BATERIE = 0xF;
	private static final int MASK_ORDRE = 4;
	
	/**
	 * Offset pour reupperer la donnee
	 */
	private static final int OFFSET_ETAT_POMPE =7;
	private static final int OFFSET_NOMBRE_DE_NIVEAU = 2;
	private static final int OFFSET_NIVEAU_P1 = 3;
	private static final int OFFSET_NIVEAU_P2 = 5;
	private static final int OFFSET_NIVEAU_ORDRE = 3;
	
	/**
	 * Transforme un ordre en tableau de byte
	 * @param o
	 * @return
	 */
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
	
	/**
	 * tranforme un tableau de byte en data
	 * @param b
	 * @return
	 */
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
	
	/**
	 * transforme du json en ordre
	 * @param json
	 * @return
	 */
	public static Ordre JSONtoOrder(String json)
	{
		try 
		{
			JSONObject obj = new JSONObject(json);
			String msg = obj.getString("message");
			if(msg.equals("True"))
			{
				JSONObject value = obj.getJSONObject("value");
				String id = value.getString("idDevice");
				return new Ordre(value.getString("idDevice"), value.getString("date"), value.getString("time"), value.getString("level_require"));
			}
			
		} catch (JSONException e) {e.printStackTrace();}
		
		
		return null;
	}

}
