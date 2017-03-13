package ecoute;

public class Data 
{

	private int idDevice;
	private int state;
	private int levelMax;
	private int level;
	private int levelpower;
	
	public Data(int id, int state, int levelMax, int level, int levelb) {
		this.idDevice = id;
		this.state = state;
		this.level = level;
		this.levelMax = levelMax;
		this.levelpower = levelb;
	}

	public int getIdDevice() {
		return idDevice;
	}

	public int getState() {
		return state;
	}

	public int getLevelMax() {
		return levelMax;
	}

	public int getLevel() {
		return level;
	}

	public int getLevelpower() {
		return levelpower;
	}

	
	public String toString()
	{
		return this.idDevice+"-"+this.state+"-"+this.levelMax+"-"+this.level+"-"+this.levelpower;
	}

}
