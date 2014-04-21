package dblayout;

import android.content.ContentValues;

public class Delete extends DatabaseConnector {
	public Delete() { } //empty constructor
	
	public void deleteGoalById(long id) {
		open(); // open the database
	    database.delete("goal", "_id=" + id, null);
	    close(); // close the database
	}
	
	public void deleteWorkoutById(long id) {
		open(); // open the database
	    database.delete("workout", "_id=" + id, null);
	    close(); // close the database
	}
	
	//delete image_path associated with workout
	public void deleteImage(long id) {
		open();
		//have to update with an empty string which means need to check if empty string for path (if path not found etc)
		ContentValues values = new ContentValues();
		values.put("image_path", "");
		database.update("workout", values, "_id=" + id, null);
		close();
	}
	
	//delete audio_file associated with workout
	public void deleteAudio(long id) {
		open();
		ContentValues values = new ContentValues();
		values.put("audio_file", "");
		database.update("workout", values, "_id=" + id, null);
		close();
	}
	
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
