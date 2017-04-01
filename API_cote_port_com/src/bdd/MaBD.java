package bdd;

/**
 * @author Héloïse
 *
 */
public class MaBD {
	
	/**
	 * URL vers la base de donnee(ne fonctionne que en local)
	 */
	private static String url = "jdbc:mysql://localhost:3306/pompe_connectee";
	
	/**
	 * login pour acceder à la base de donnee
	 */
	private static String utilisateur = "root";
	
	/**
	 * mot de passe pour acceder à la base de donnee 
	 */
	private static String motDePasse = "";
	//private static String motDePasse = "pompeCo";
	
	/**
	 * Mot de passe d'un jardinier
	 */
	private static String mdpJardinier = "pompe";
	/**
	 * Login d'un jardinier
	 */
	private static String LoginJardinier = "lejardinier";
	
	/**
	 * @return
	 */
	public static String getMdpJardinier() {
		return mdpJardinier;
	}
	/**
	 * @return
	 */
	public static String getLoginJardinier() {
		return LoginJardinier;
	}
	/**
	 * @return
	 */
	public static String getUrl() {
		return url;
	}
	/**
	 * @return
	 */
	public static String getUtilisateur() {
		return utilisateur;
	}
	/**
	 * @return
	 */
	public static String getMotDePasse() {
		return motDePasse;
	}
	
	
	
	

}
