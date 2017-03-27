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
 * La classe qui suit permet d'ouvrir une connexion sur un port s�rie. 
 * Param�tre par defaut: 9600 baudes, sans contr�le de flux
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
	 * Ouvre le port et v�rifie que le port n'est pas utilis�
	 * */
	public Boolean ouvrirPort()
	{
		 boolean isOpen = false;	 
		 try 
		 {
			 this.port = new SerialPort(this.portID);
			 isOpen = this.port.openPort();	
			 this.port.setParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
			 System.out.println("Le port a �t� ouvert");
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
			System.out.println("Le port a �t� ferm�");
		} catch (SerialPortException e) {e.printStackTrace();}
		
	}
	
	/**
	 * Entrees:byte[]
	 * Sorties:void
	 * Ecrit des donn�es
	 * */
	public void write(byte[] message)	
	{
		try 
		{
			this.port.writeBytes(message);
			this.port.writeByte((byte) 0x0A);
		} catch (SerialPortException e) {e.printStackTrace();}
	}
	
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
	 * Entrees:void
	 * Sorties:byte[]
	 * Lit des donn�es
	 * */
	public byte[] read()
	{
		
		byte[] ordre = null;
		try {
			Thread.sleep(500);
			ordre = port.readBytes();
			//ordre = this.port.readBytes(translator.TAILLE_TRAME_DATA);
		} catch (SerialPortException | InterruptedException e) {e.printStackTrace();}
		return ordre;
	}
	
	public void listener(SerialPortEventListener se)
	{
		try {this.port.addEventListener(se);} catch (SerialPortException e) {e.printStackTrace();}
	}
		
}