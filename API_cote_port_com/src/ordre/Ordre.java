package ordre;

/**
 * @author Héloïse
 *
 */
public class Ordre {
	

	
	
	/**
	 * id de la device concerne par l'ordre
	 */
	private String idDevice;
	/**
	 * date de l'ajout de l'ordre
	 */
	private String date;
	/**
	 * heure de l'ajout de l'orde
	 */
	private String time;
	/**
	 * niveau requis
	 */
	private int level_require;
	
	/**
	 * COnstructeur
	 * @param idDevice
	 * @param date
	 * @param time
	 * @param level_require
	 */
	public Ordre(String idDevice, String date, String time, String level_require) {
		this.idDevice = idDevice;
		this.date = date;
		this.time = time;
		this.level_require = new Integer(level_require);
	}
	
	
	public String getIdDevice() {return idDevice;}
	public String getDate() {return date;}
	public String getTime() {return time;}
	public int getLevel_require() {return level_require;}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return this.idDevice+"-"+this.date+"-"+this.time+"-"+this.level_require;
	}

}
