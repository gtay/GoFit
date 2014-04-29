package entities;

public class Goal {
	private int id;
	private String name;
	private String end_date;
	private String progress;
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEndDate() {
		return end_date;
	}
	
	public String getProgress() {
		return progress;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEndDate(String end_date) {
		this.end_date = end_date;
	}
	
	public void setProgress(String progress) {
		this.progress = progress;
	}
	
	public void setID(int id) {
		this.id = id;
	}
}
