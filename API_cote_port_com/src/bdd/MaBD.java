package bdd;

public class MaBD {
	
	private static String url = "jdbc:mysql://localhost:3306/pompe_connectee";
	//private static String url = "jdbc:mysql://192.168.1.15:3306/pompe_connectee";
	
	private static String utilisateur = "root";
	//private static String motDePasse = "";
	private static String motDePasse = "pompeCo";
	
	public static String getUrl() {
		return url;
	}
	public static String getUtilisateur() {
		return utilisateur;
	}
	public static String getMotDePasse() {
		return motDePasse;
	}
	
	
	
	

}
