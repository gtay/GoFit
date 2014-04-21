package entities;

public class Achievement {
	private String num_completed;
	private String fastest_time;
	
	public Achievement() { }
	
	public String getNumCompleted() {
		return num_completed;
	}
	
	public String getFastestTime() {
		return fastest_time;
	}
	
	public void setNumCompleted(String num_completed) {
		this.num_completed = num_completed;
	}
	
	public void setFastestTime(String fastest_time) {
		this.fastest_time = fastest_time;
	}
}
