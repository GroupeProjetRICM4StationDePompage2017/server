package ecoute;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

/**
 * La classe qui suit permet d'ouvrir une connexion sur un port série. 
 * Paramètre par defaut: 9600 baudes, sans contrôle de flux
 * 
 * Attributs:SerialPort port, CommPortIdentifier portID
 * 
 * */
public class SerialPortConnexion 
{
	private SerialPort port;
	private CommPortIdentifier portID;
	
	/**Constructeur
	 * @throws NoSuchPortException */
	public SerialPortConnexion(String nom) throws NoSuchPortException
	{
		if (verificationExistancePort(nom)!=null)
		{
			this.portID=verificationExistancePort(nom);
			this.port=null;
		}
	}
	
	/**
	 * Entrees:void
	 * Sorties:void
	 * Ouvre le port et vérifie que le port n'est pas utilisé
	 * */
	public Boolean ouvrirPort()
	{
		if(this.portID==null){return false;}
		try 
		{
			 this.port = (SerialPort) portID.open("SerialSender", 2000);
			 System.out.println("Le port a été ouvert");
		}
		catch (PortInUseException e2) 
		{
			System.out.println("Le port est déja utilisé");
			return false;
		}
		this.parametrageConnection();
		try 
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {}
		return true;
	}

	/**
	 * Entrees:void
	 * Sorties:void
	 * Parametre la connexion et le controle de flux de celle-ci
	 * */
	private void parametrageConnection() 
	{
		try
		{
			 this.port.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
			 this.port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
			 this.port.enableReceiveTimeout(5000);
			 //this.port.setInputBufferSize(120000);
		}
		catch (UnsupportedCommOperationException e3) 
		{
			System.out.println("Mauvaise configuration");
			return;
		}
		
	}
	
	/**
	 * Entrees:void
	 * Sorties:void
	 * Ferme la connexion
	 * */
	public void fermerPort()
	{
		this.port.close();
		System.out.println("Le port a été fermé");
	}
	
	/**
	 * Entrees:String, le nom du port série
	 * Sorties:CommPortIdentifier, identifiant du port
	 * Verifie si la connexion sur le port est possible et si oui, la methode renvoie identifiant du port
	 * @throws NoSuchPortException 
	 * */
	private CommPortIdentifier verificationExistancePort(String nomPort) throws NoSuchPortException
	{
		 CommPortIdentifier p = null;
		 
		 p = CommPortIdentifier.getPortIdentifier(nomPort);
		 return p;
		 
	}
	
	/**
	 * Entrees:void
	 * Sorties:InputStream
	 * Revoie la connexion d'entree
	 * */
	public InputStream obtenirConnexionEntree()
	{
		try
		{
			return this.port.getInputStream();
		}
		catch(IOException e4) 
		{
			e4.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * Entrees:void
	 * Sorties:OutputStream
	 * Renvoie la connexion de sortie
	 * */
	public OutputStream obtenirConnexionSortie()
	{
		try
		{
			return this.port.getOutputStream();
		}
		catch(IOException e4) 
		{
			e4.printStackTrace();
			return null;
		}
	}
		
}