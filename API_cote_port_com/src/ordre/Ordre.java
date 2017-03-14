package ordre;

public class Ordre {
	

	
	
	private String idDevice;
	private String date;
	private String time;
	private int level_require;
	
	public Ordre(String idDevice, String date, String time, String level_require) {
		this.idDevice = idDevice;
		this.date = date;
		this.time = time;
		this.level_require = new Integer(level_require);
	}
	//TODO
	public String getIdDevice() {return "36";}

	public String getDate() {return date;}

	public String getTime() {return time;}

	public int getLevel_require() {return level_require;}
	
	public String toString()
	{
		return this.idDevice+"-"+this.date+"-"+this.time+"-"+this.level_require;
	}

}
