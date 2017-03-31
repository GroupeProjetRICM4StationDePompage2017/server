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
import ecoute.translator;

public class PHP implements BDD {
	
	private final static String URL_GET_ORDRE = "http://192.168.1.15/server/getOrdre.php";
	private final static String URL_SET_DATA = "http://192.168.1.15/server/ajouter_valeur.php";
	private final static String URL_UPDATE_ORDRE = "http://192.168.1.15/server/updateOrdre.php";
	
	private SimpleDateFormat formaterDate = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat formaterTime = new SimpleDateFormat("kk:mm:ss");
    private HttpURLConnection urlConnection = null;
	
	public PHP() {}	
	
	private String getJSON(String urlString)
	{
		String json = "";
		try {
			URL url = new URL(urlString);
			
			urlConnection = (HttpURLConnection) url.openConnection();
	        urlConnection.setRequestMethod("GET");
	        urlConnection.setReadTimeout(10000 /* milliseconds */);
	        urlConnection.setConnectTimeout(15000 /* milliseconds */);
	        urlConnection.setDoOutput(true);
	        urlConnection.connect();
	        
	        BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = br.readLine()) != null) {
	            sb.append(line+"\n");
	        }
	        br.close();
	        
	        
	        urlConnection.disconnect();
	        
	        json = sb.toString();
	        json = json.replaceAll("[\n]","");
	        json = json.replaceAll("[\\[]","");
	        json = json.replaceAll("[\\]]","");
	        
		} catch (Exception e) {e.printStackTrace();	}
		return json;
	}
	
	private String sendPost(String urlString,String param)
	{
		
		try {
			URL url = new URL(urlString);
			this.urlConnection = (HttpURLConnection) url.openConnection();
			this.urlConnection.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(this.urlConnection.getOutputStream());
			writer.write(param);
			writer.flush();
			
			 BufferedReader br=new BufferedReader(new InputStreamReader(this.urlConnection.getInputStream()));

		     String jsonString = new String();
		     StringBuilder sb = new StringBuilder();
		     String line;
		     while ((line = br.readLine()) != null) {
		            sb.append(line+"\n");
		     }
		     br.close();
		        
			this.urlConnection.disconnect();
			
			jsonString = sb.toString();
			jsonString = jsonString.replaceAll("[\n]","");
			jsonString = jsonString.replaceAll("[\\[]","");
			jsonString = jsonString.replaceAll("[\\]]","");
			
			return jsonString;
		} catch (Exception e) {e.printStackTrace();	}
		return null;
	}
	
	public Ordre getOrder(int id)
	{
		URL url;
		try {
			String res = getJSON(URL_GET_ORDRE+"?id="+id);
			
			System.out.println(res);
			

			Ordre o = translator.JSONtoOrder(res);
			System.out.println(o.toString());
			
            return o;
			
		} catch (Exception e) {e.printStackTrace();	}
		return null;
	}
	
	
	
	public void setData(Data d)
	{
		Date date = new Date();
		String post=d.postParameter()+"&date="+formaterDate.format(date).toString()+"&time="+formaterTime.format(date).toString();
		String res = this.sendPost(URL_SET_DATA, post);
		System.out.println(res);
		
	}
	
	public void updateOrdre(int id)
	{
		String post="id="+id+"&login="+MaBD.getLoginJardinier()+"&mdp="+MaBD.getMdpJardinier();
		String res = this.sendPost(URL_UPDATE_ORDRE,post);
		System.out.println(res);
	}

	@Override
	public void connexion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fermutureConnexion() {
		// TODO Auto-generated method stub
		
	}

}
