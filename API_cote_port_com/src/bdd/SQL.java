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


public class SQL {

	private SimpleDateFormat formaterDate = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat formaterTime = new SimpleDateFormat("kk:mm:ss");
	private Connection connexion;
	
	public SQL() {}
	
	public void connexion()
	{
		this.connexion = null;
		try {
		    connexion = (Connection) DriverManager.getConnection( MaBD.getUrl(), MaBD.getUtilisateur(), MaBD.getMotDePasse());
		    System.out.println("Vous etes bien connecte");
		} catch ( SQLException e ) {System.out.println("Erreur lors de la connexion : ");e.printStackTrace();}
	}
	
	public void fermutureConnexion()
	{
		if ( this.connexion != null )
		{
			try {this.connexion.close();} catch ( SQLException ignore ) {}
			System.out.println("La connexion a ete fermee");
		}
	}
	
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
	
	public void updateOrdre(int id)
	{
		try 
		{
			Statement statement = (Statement) connexion.createStatement();	
			int statut = statement.executeUpdate( "UPDATE ordres SET is_executed=1 WHERE is_executed=0 and idDevice="+id+";" );
			statement.close();
		} catch (SQLException e) {e.printStackTrace();}
	}
	
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
