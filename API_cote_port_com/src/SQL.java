import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class SQL {
	
	/* Connexion à la base de données */
	private String url = "jdbc:mysql://localhost:3306/pompe_connectee";
	private String utilisateur = "root";
	private String motDePasse = "";
	private SimpleDateFormat formaterDate = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat formaterTime = new SimpleDateFormat("kk:mm:ss");
	
	private Connection connexion;
	
	public SQL() {
		this.connexion();
	}
	
	public void connexion()
	{
		this.connexion = null;
		try {
		    connexion = (Connection) DriverManager.getConnection( url, utilisateur, motDePasse );
		    System.out.println("Connecté");
		} catch ( SQLException e ) {
			System.out.println("Erreur lors de la connexion");
		}
	}
	
	public void fermutureConnexion()
	{
		if ( this.connexion != null )
		{
			try {this.connexion.close();} catch ( SQLException ignore ) {}
		}
	}
	
	public void getOrder()
	{
		try 
		{
			Statement statement = (Statement) connexion.createStatement();
			
			String requete = "SELECT * FROM ordres WHERE idOrdre = (SELECT MAX(idOrdre) FROM ordres WHERE level_require = (SELECT MAX(level_require) FROM ordres WHERE is_executed=0))";
			//ResultSet resultat = statement.executeQuery( "SELECT * FROM ordres WHERE idOrdre = (SELECT MAX(idOrdre) FROM ordres WHERE is_executed=0);" );
			ResultSet resultat = statement.executeQuery( requete);
			while(resultat.next())
			{
				System.out.println(resultat.getString( "idDevice" )+"-"+resultat.getString( "date" )+"-"+resultat.getString( "time" )+"-"+resultat.getString( "level_require" )+"-"+resultat.getString( "is_executed" ));
			}
			statement.close();
			//statement = (Statement) connexion.createStatement();
			//int statut = statement.executeUpdate( "UPDATE ordres SET is_executed=1 WHERE is_executed=0 ;" );
			//statement.close();
		} catch (SQLException e) {e.printStackTrace();}
	}
	
	public void setData(int id,int level,int state,int levelb)
	{
		try 
		{
			if(level>=0 && level<9 && levelb>=0 && levelb<11)
			{
				Date date = new Date();
				Statement statement = (Statement) connexion.createStatement();
				String requete = "INSERT INTO cuvemeasure VALUES ("+id+",\'"+formaterDate.format(date).toString()+"\',\'"+formaterTime.format(date).toString()+"\',"+level+","+state+","+levelb+");";
				System.out.println(requete);
				int statut = statement.executeUpdate( requete );
				statement.close();
			}
			
			
		} catch (SQLException e) {e.printStackTrace();}
		
	}
	
	public static void main(String args[]) {
        SQL coSQL = new SQL();
        coSQL.getOrder();
        
        //Random rand = new Random();
        //coSQL.setData(1,rand.nextInt(9),rand.nextInt(2),rand.nextInt(11));
        coSQL.fermutureConnexion();
    }

}
