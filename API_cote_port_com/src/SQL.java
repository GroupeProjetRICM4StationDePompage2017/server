import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;


public class SQL {
	
	/* Connexion à la base de données */
	private String url = "jdbc:mysql://localhost:3306/pompe_connectee";
	private String utilisateur = "root";
	private String motDePasse = "";
	
	private Connection connexion;
	
	public SQL() {
		this.connexion();
	}
	
	public void connexion()
	{
		this.connexion = null;
		try {
		    connexion = (Connection) DriverManager.getConnection( url, utilisateur, motDePasse );
		} catch ( SQLException e ) {
			System.out.println("Erreur lors de la connexion");
		} finally {
		    if ( this.connexion != null )
		        try {this.connexion.close();} catch ( SQLException ignore ) {}
		}	
	}
	
	public void fermutureConnexion()
	{
		if ( this.connexion != null )
		{
			try {this.connexion.close();} catch ( SQLException ignore ) {}
		}
	}
	
	public void getOrder(){}
	
	public void setData(){}
	
	public static void main(String args[]) {
        SQL coSQL = new SQL();
        coSQL.fermutureConnexion();
    }

}
