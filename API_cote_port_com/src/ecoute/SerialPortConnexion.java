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
 * La classe qui suit permet d'ouvrir une connexion sur un port serie. 
 * Parametre par defaut: 9600 baudes, sans controle de flux
 * 
 * Attributs:SerialPort port, String portID
 * 
 * */
/**
 * @author Héloïse
 *
 */
public class SerialPortConnexion
{
	private SerialPort port;
	private String portID;
	

	/**
	 * Constructeur
	 * @param nom
	 */
	public SerialPortConnexion(String nom) //throws NoSuchPortException
	{
		this.portID=nom;
		this.port=null;	
	}

	/**
	 * Ouvre le port, attention une erreur sera souleve si le port est deja utilise
	 * @return
	 */
	public Boolean ouvrirPort()
	{
		 boolean isOpen = false;	 
		 try 
		 {
			 this.port = new SerialPort(this.portID);
			 isOpen = this.port.openPort();	
			 this.port.setParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
			 System.out.println("Le port a ete ouvert");
		} catch (SerialPortException e1) {
			e1.printStackTrace();
			return isOpen;
		}
		return isOpen;		
	}
	
	
	/**
	 * Fermer le port
	 */
	public void fermerPort()
	{
		try {
			this.port.closePort();
			System.out.println("Le port a ete ferme");
		} catch (SerialPortException e) {e.printStackTrace();}
		
	}
	
	
	/**
	 * Envoie un message sur le port
	 * @param message
	 */
	public void write(byte[] message)	
	{
		try 
		{
			this.port.writeBytes(message);
			this.port.writeByte((byte) 0x0A);
		} catch (SerialPortException e) {e.printStackTrace();}
	}
	
	/**
	 * Envoie un message sur le port octet par octet
	 * @param message
	 */
	public void write2(byte[] message)	
	{
		try 
		{
			for(int i = 0; i < message.length;i++)
			{
				this.port.writeByte(message[i]);
			}
			this.port.writeByte((byte) 0x0A);
		} catch (SerialPortException e) {e.printStackTrace();}
	}
	
	/**
	 * Lit un tableau de byte sur le port
	 * @return
	 */
	public byte[] read()
	{
		
		byte[] ordre = null;
		try {
			Thread.sleep(500);
		    ordre = port.readBytes();
			//ordre = port.readBytes(Translator.TAILLE_TRAME_DATA+1);
			//ordre = port.readBytes(Translator.TAILLE_TRAME_DATA+2);
		} catch (SerialPortException | InterruptedException e) {e.printStackTrace();}
		return ordre;
	}
	
	/**
	 * Ajout d'unlistener sur le port
	 * @param se
	 */
	public void listener(SerialPortEventListener se)
	{
		try {this.port.addEventListener(se);} catch (SerialPortException e) {e.printStackTrace();}
	}
		
}