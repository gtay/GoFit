package entities;

public class Exercise {
	private int id;
	private String name;
	private String details;
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDetails() {
		return details;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDetails(String details) {
		this.details = details;
	}
	
	public void setID(int id) {
		this.id = id;
	}
}
