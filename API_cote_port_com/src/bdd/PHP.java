package bdd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

import ordre.Ordre;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import ecoute.Data;
import ecoute.Translator;

/**
 * @author Héloïse
 * Cette classe permet d'acceder à la base de donee via l'api php
 *
 */
public class PHP implements BDD {
	
	/**
	 * Les URL des différentes page
	 */
	private final static String URL_GET_ORDRE = "http://192.168.1.15/getOrdre.php";
	private final static String URL_SET_DATA = "http://192.168.1.15/ajouter_valeur.php";
	private final static String URL_UPDATE_ORDRE = "http://192.168.1.15/updateOrdre.php";
	
	
	
	/**
	 * Constructeur
	 */
	public PHP() {}	
	
	/**
	 * Execute une requete get en fonction d'un url passe en parametre
	 * @param urlString
	 * @return JSON sours format text
	 */
	private String getJSON(String urlString)
	{
		String json = "";
		try {
			URL url = new URL(urlString);
			
			//Connexion
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
	        urlConnection.setRequestMethod("GET");
	        urlConnection.setReadTimeout(10000);
	        urlConnection.setConnectTimeout(15000);
	        urlConnection.setDoOutput(true);
	        urlConnection.connect();
	        
	        
	        //Recup resultat
	        BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = br.readLine()) != null) {
	            sb.append(line+"\n");
	        }
	        br.close();
	        urlConnection.disconnect();
	        
	        //Mettre donnees au bon format
	        json = sb.toString();
	        json = json.replaceAll("[\n]","");
	        json = json.replaceAll("[\\[]","");
	        json = json.replaceAll("[\\]]","");
	        
		} catch (Exception e) {e.printStackTrace();	}
		return json;
	}
	
	/**
	 * Execute une requete get en fonction d'un url et de parametre passe en parametre
	 * @param urlString
	 * @param param parametre sous la forme de paramettre http
	 * @return JSON sous format texte
	 */
	private String sendPost(String urlString,String param)
	{
		
		try {
			URL url = new URL(urlString);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
			writer.write(param);
			writer.flush();
			
			 BufferedReader br=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

		     String jsonString = new String();
		     StringBuilder sb = new StringBuilder();
		     String line;
		     while ((line = br.readLine()) != null) {
		            sb.append(line+"\n");
		     }
		     br.close();
		        
			urlConnection.disconnect();
			
			jsonString = sb.toString();
			jsonString = jsonString.replaceAll("[\n]","");
			jsonString = jsonString.replaceAll("[\\[]","");
			jsonString = jsonString.replaceAll("[\\]]","");
			
			return jsonString;
		} catch (Exception e) {e.printStackTrace();	}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see bdd.BDD#getOrder(int)
	 */
	public Ordre getOrder(int id)
	{
		URL url;
		try {
			String res = getJSON(URL_GET_ORDRE+"?id="+id);
			
			System.out.println(res);
			

			Ordre o = Translator.JSONtoOrder(res);
			//if(o!=null){System.out.println(o.toString());}
			
            return o;
			
		} catch (Exception e) {e.printStackTrace();	}
		return null;
	}
	
	
	
	/* (non-Javadoc)
	 * @see bdd.BDD#setData(ecoute.Data)
	 */
	public void setData(Data d)
	{
		Date date = new Date();
		String post=d.postParameter()+"&date="+formaterDate.format(date).toString()+"&time="+formaterTime.format(date).toString();
		String res = this.sendPost(URL_SET_DATA, post);
		//System.out.println(res);
		
	}
	
	/* (non-Javadoc)
	 * @see bdd.BDD#updateOrdre(int)
	 */
	public void updateOrdre(int id)
	{
		String post="id="+id+"&login="+MaBD.getLoginJardinier()+"&mdp="+MaBD.getMdpJardinier();
		String res = this.sendPost(URL_UPDATE_ORDRE,post);
		//System.out.println(res);
	}

	/* (non-Javadoc)
	 * @see bdd.BDD#connexion()
	 */
	@Override
	public void connexion() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see bdd.BDD#fermutureConnexion()
	 */
	@Override
	public void fermutureConnexion() {
		// TODO Auto-generated method stub
		
	}

}
