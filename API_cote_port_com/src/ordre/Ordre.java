package ordre;

public class Ordre {
	
	private String idDevice;
	private String date;
	private String time;
	private String level_require;
	
	public Ordre(String idDevice, String date, String time, String level_require) {
		this.idDevice = idDevice;
		this.date = date;
		this.time = time;
		this.level_require = level_require;
	}

	public String getIdDevice() {return idDevice;}

	public String getDate() {return date;}

	public String getTime() {return time;}

	public String getLevel_require() {return level_require;}
	
	
	
	

}
