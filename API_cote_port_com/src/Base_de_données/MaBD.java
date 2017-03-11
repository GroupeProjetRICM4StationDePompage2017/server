package Base_de_données;

public class MaBD {
	
	private static String url = "jdbc:mysql://localhost:3306/pompe_connectee";
	private static String utilisateur = "root";
	private static String motDePasse = "";
	
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
