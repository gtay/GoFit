package dblayout;

public class Delete extends DatabaseConnector {
	public Delete() { } //empty constructor
	
	public void deleteGoal(long id) {
		open(); // open the database
	    database.delete("goal", "_id=" + id, null);
	    close(); // close the database
	}
	
	public void deleteWorkout(long id) {
		open(); // open the database
	    database.delete("workout", "_id=" + id, null);
	    close(); // close the database
	}
	
	//delete image_path associated with workout
	public void deleteImage(long id) {
		open();
		//have to update with an empty string which means need to check if empty string for path (if path not found etc)
		close();
	}
	
	//delete audio_file associated with workout
	
	public void deleteAchievement(long id) {
		open(); // open the database
	    database.delete("achievement", "_id=" + id, null);
	    close(); // close the database
	}
	
	public void deleteExercise(long id) {
		open(); // open the database
	    database.delete("exercise", "_id=" + id, null);
	    close(); // close the database
	}

}
