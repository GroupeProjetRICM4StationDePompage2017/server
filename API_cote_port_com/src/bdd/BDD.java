package bdd;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ordre.Ordre;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import ecoute.Data;


/**
 * @author Héloïse
 *
 */
public interface BDD {
	
	/**
	 *formateur de date 
	 */
	SimpleDateFormat formaterDate = new SimpleDateFormat("yyyy-MM-dd");
    
	/**
     * formater de temps
     */
    SimpleDateFormat formaterTime = new SimpleDateFormat("kk:mm:ss");
	
	
	/**
	 * Recupere un ordre non executer dans la base de donnee
	 * @param id id de la device concernee
	 * @return Ordre
	 */
	public Ordre getOrder(int id);
	
	/**
	 * Passe un ordre non execute a executer
	 * @param id id de la device
	 */
	public void updateOrdre(int id);
	
	/**
	 * Enregistre une nouvelle valeur dans la table cuvemeasure
	 * @param d les données à enregistrer
	 */
	public void setData(Data d);

	/**
	 * Ouvre la connexion vers la base de donnée
	 */
	public void connexion();
	
	/**
	 * Ferme la connexion vers la base de donne
	 */
	public void fermutureConnexion();
	
}
