package dblayout;

import android.content.ContentValues;
import entities.*;

public class Update extends DatabaseConnector {
	
	public Update() { }
	
	public void updateUser(User user) {
		open();
		
		ContentValues values = new ContentValues();
		values.put("name", user.getName());
		values.put("age", user.getAge());
		values.put("height", user.getHeight());
		values.put("weight", user.getWeight());
		
		//double check that id is 1
		database.update(USER_TABLE, values, "_id=1", null);
		close();
	}
	
	public void updateAchievements(Achievement achievement) {
		open();
		
		ContentValues values = new ContentValues();
		values.put("fastest_time", achievement.getFastestTime());
		values.put("num_completed", achievement.getNumCompleted());
		
		database.update(ACHIEVE_TABLE, values, "_id=1", null);
		close();
	}
	
	public void updateGoal(Goal goal) {
		open();
		
		ContentValues values = new ContentValues();
		values.put("name", goal.getName());
		values.put("end_date", goal.getEndDate());
		values.put("progress", goal.getProgress());
		
		database.update(GOAL_TABLE, values, "_id="+goal.getID(), null);
		close();
	}
	
	public void updateWorkout(Workout workout) {
		open();
		
		ContentValues values = new ContentValues();
		values.put("date", workout.getDate());
		values.put("details", workout.getDetails());
		values.put("image_path", workout.getImagePath());
		values.put("audio_file", workout.getAudioPath());
		
		database.update(WORK_TABLE, values, "_id="+workout.getID(), null);
		close();
	}
	
	public void updateExercise(Exercise exercise) {
		open();
		
		ContentValues values = new ContentValues();
		values.put("name", exercise.getName());
		values.put("weight", exercise.getWeight());
		values.put("sets", exercise.getSets());
		values.put("reps", exercise.getReps());
		
		database.update(EXERCISE_TABLE, values, "_id="+exercise.getID(), null);
		close();
	}

}
