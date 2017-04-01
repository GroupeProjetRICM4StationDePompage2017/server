package bdd;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import ordre.Ordre;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import ecoute.Data;


/**
 * @author Héloïse
 *
 */
public class SQL implements BDD {

	
	/**
	 * Permet la connexion avec la base de donnee
	 */
	private Connection connexion;
	
	/**
	 * Constructeur
	 */
	public SQL() {}
	
	/* (non-Javadoc)
	 * @see bdd.BDD#connexion()
	 */
	public void connexion()
	{
		this.connexion = null;
		try {
		    connexion = (Connection) DriverManager.getConnection( MaBD.getUrl(), MaBD.getUtilisateur(), MaBD.getMotDePasse());
		    System.out.println("Vous etes bien connecte");
		} catch ( SQLException e ) {System.out.println("Erreur lors de la connexion : ");e.printStackTrace();}
	}
	
	/* (non-Javadoc)
	 * @see bdd.BDD#fermutureConnexion()
	 */
	public void fermutureConnexion()
	{
		if ( this.connexion != null )
		{
			try {this.connexion.close();} catch ( SQLException ignore ) {}
			System.out.println("La connexion a ete fermee");
		}
	}
	
	/* (non-Javadoc)
	 * @see bdd.BDD#getOrder(int)
	 */
	public Ordre getOrder(int id)
	{
		try 
		{
			Statement statement = (Statement) connexion.createStatement();
			
			String requete = "SELECT * FROM ordres WHERE is_executed=0 and idDevice="+id+";";
			
			ResultSet resultat = statement.executeQuery(requete);
			resultat.next();
			
			
			if(resultat.getFetchSize()==0)
			{
				statement.close();
				return null;
			}
			Ordre o = new Ordre(resultat.getString( "idDevice" ), resultat.getString( "date" ), resultat.getString( "time" ), resultat.getString( "level_require" ));
			statement.close();
			
			return o;
				
		} catch (SQLException e) {e.printStackTrace();}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see bdd.BDD#updateOrdre(int)
	 */
	public void updateOrdre(int id)
	{
		try 
		{
			Statement statement = (Statement) connexion.createStatement();	
			int statut = statement.executeUpdate( "UPDATE ordres SET is_executed=1 WHERE is_executed=0 and idDevice="+id+";" );
			statement.close();
		} catch (SQLException e) {e.printStackTrace();}
	}
	
	/* (non-Javadoc)
	 * @see bdd.BDD#setData(ecoute.Data)
	 */
	public void setData(Data d)
	{
		try 
		{
			Date date = new Date();
			Statement statement = (Statement) connexion.createStatement();
			String requete = "INSERT INTO cuvemeasure VALUES ("+d.getIdDevice()+",\'"+formaterDate.format(date).toString()+"\',\'"+formaterTime.format(date).toString()+"\',"+d.getLevel()+","+d.getState()+","+d.getLevelpower()+");";
			int statut = statement.executeUpdate( requete );
			statement.close();
			
		} catch (SQLException e) {e.printStackTrace();}
		
	}
	

}
