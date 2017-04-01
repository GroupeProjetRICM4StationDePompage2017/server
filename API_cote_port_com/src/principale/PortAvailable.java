package principale;

/**
 * @author Héloïse
 *
 */
public class PortAvailable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] tableau = jssc.SerialPortList.getPortNames();
		for(int i = 0; i<tableau.length; i++)
		{
			System.out.println(tableau[i]);
		}

	}

}
