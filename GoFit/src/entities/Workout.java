package entities;

public class Workout {
	private int goal_id;
	private String date;
	private String details;
	private String image_path;
	private String audio_file;
	
	public int getGoalId() {
		return goal_id;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getDetails() {
		return details;
	}
	
	public String getImagePath() {
		return image_path;
	}
	
	public String getAudioPath() {
		return audio_file;
	}
	
	public void setGoalId(int goal_id) {
		this.goal_id = goal_id;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public void setDetails(String details) {
		this.details = details;
	}
	
	public void setImagePath(String image_path) {
		this.image_path = image_path;
	}
	
	public void setAudioPath(String audio_file) {
		this.audio_file = audio_file;
	}
}
