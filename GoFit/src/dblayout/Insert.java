package dblayout;

import android.content.ContentValues;
import android.util.Log;
import entities.*;

public class Insert extends DatabaseConnector {

	public Insert() {}
	
	//no adds for user and achievement since only one row is expected
	//any changes are done with update
	
	public void addGoal(Goal goal) {
		//for debugging
		Log.d("addGoal", goal.toString());
		open();
		
		ContentValues values = new ContentValues();
		values.put("name", goal.getName());
		values.put("end_date", goal.getEndDate());
		values.put("progress", goal.getProgress());
		
		database.insert(GOAL_TABLE, null, values);
		close();
	}
	
	public void addWorkout(Workout workout) {
		//for debugging
		Log.d("addWorkout", workout.toString());
		open();
		
		ContentValues values = new ContentValues();
		values.put("goal_id", workout.getGoalId());
		values.put("date", workout.getDate());
		values.put("details", workout.getDetails());
		values.put("image_path", workout.getImagePath());
		values.put("audio_file", workout.getAudioPath());
		
		database.insert(WORK_TABLE, null, values);
		close();
	}
	
	public void addExercise(Exercise exercise) {
		//for debugging
		Log.d("addExercise", exercise.toString());
		open();
		
		ContentValues values = new ContentValues();
		values.put("name", exercise.getName());
		values.put("weight", exercise.getWeight());
		values.put("sets", exercise.getSets());
		values.put("reps", exercise.getReps());
		
		database.insert(EXERCISE_TABLE, null, values);
		close();
	}
}
