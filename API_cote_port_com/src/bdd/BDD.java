package bdd;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import ordre.Ordre;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import ecoute.Data;

public interface BDD {
	
	public Ordre getOrder(int id);
	
	public void updateOrdre(int id);
	
	public void setData(Data d);

	public void connexion();
	
	public void fermutureConnexion();
	
}
