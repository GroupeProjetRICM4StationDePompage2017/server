package ecoute;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jssc.SerialPort;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/*import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
*/
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
	private String portID;
	
	/**Constructeur
	 * @throws NoSuchPortException */
	public SerialPortConnexion(String nom) //throws NoSuchPortException
	{
		this.portID=nom;
		this.port=null;
		
	}
	
	/**
	 * Entrees:void
	 * Sorties:void
	 * Ouvre le port et vérifie que le port n'est pas utilisé
	 * */
	public Boolean ouvrirPort()
	{
		 boolean isOpen = false;	 
		 try 
		 {
			 this.port = new SerialPort(this.portID);
			 isOpen = this.port.openPort();
			 this.port.setParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
			 System.out.println("Le port a été ouvert");
		} catch (SerialPortException e1) {
			e1.printStackTrace();
			return isOpen;
		}
		return isOpen;		
	}
	
	/**
	 * Entrees:void
	 * Sorties:void
	 * Ferme la connexion
	 * */
	public void fermerPort()
	{
		try {
			this.port.closePort();
			System.out.println("Le port a été fermé");
		} catch (SerialPortException e) {e.printStackTrace();}
		
	}
	
	/**
	 * Entrees:byte[]
	 * Sorties:void
	 * Ecrit des données
	 * */
	public void write(byte[] message)	
	{
		try 
		{
			this.port.writeBytes(message);
		} catch (SerialPortException e) {e.printStackTrace();}
	}
	
	/**
	 * Entrees:void
	 * Sorties:byte[]
	 * Lit des données
	 * */
	public byte[] read()
	{
		byte[] ordre = null;
		try {
			ordre = this.port.readBytes();
		} catch (SerialPortException e) {e.printStackTrace();}
		return ordre;
	}
	
	public void listener(SerialPortEventListener se)
	{
		try {
			this.port.addEventListener(se);
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}