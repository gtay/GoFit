package dblayout;

import java.util.LinkedList;
import java.util.List;

import android.database.Cursor;
import android.util.Log;
import entities.*;

public class Read extends DatabaseConnector {
	//all the get methods
	public Read() { }
	
	//returns a list of all goals
	public List<Goal> getAllGoals() {
		List<Goal> goals = new LinkedList<Goal>();
		
		String query = "SELECT * FROM " + GOAL_TABLE;
		open();
		Cursor cursor = database.rawQuery(query, null);
		
		Goal goal = null;
		if (cursor.moveToFirst()) {
			do {
				goal = new Goal();
				int id = cursor.getColumnIndex("_id");
				int name = cursor.getColumnIndex("name");
		        int end_date = cursor.getColumnIndex("end_date");
		        int progress = cursor.getColumnIndex("progress");
		        goal.setID(Integer.parseInt(cursor.getString(id)));
				goal.setName(cursor.getString(name));
				goal.setEndDate(cursor.getString(end_date));
				goal.setProgress(cursor.getString(progress));
		        
				//add to list
				goals.add(goal);
			} while (cursor.moveToNext());
		}
		
		//for debugging
		Log.d("getAllGoals()", goals.toString());
		
		close();
		return goals;
	}
	
	//returns a list of all workouts
	public List<Workout> getAllWorkouts() {
		List<Workout> workouts = new LinkedList<Workout>();
		
		String query = "SELECT * FROM " + WORK_TABLE;
		open();
		Cursor cursor = database.rawQuery(query, null);
		
		Workout workout = null;
		if (cursor.moveToFirst()) {
			do {
				workout = new Workout();
				int id = cursor.getColumnIndex("_id");
				int goal_id = cursor.getColumnIndex("goal_id");
		        int date = cursor.getColumnIndex("date");
		        int details = cursor.getColumnIndex("details");
		        int image_path = cursor.getColumnIndex("image_path");
		        int audio_file = cursor.getColumnIndex("audio_file");
		        workout.setID(Integer.parseInt(cursor.getString(id)));
				workout.setGoalId(Integer.parseInt(cursor.getString(goal_id)));
				workout.setDate(cursor.getString(date));
				workout.setDetails(cursor.getString(details));
				workout.setImagePath(cursor.getString(image_path));
				workout.setAudioPath(cursor.getString(audio_file));
		        
				//add to list
				workouts.add(workout);
			} while (cursor.moveToNext());
		}
		
		//for debugging
		Log.d("getAllWorkouts()", workouts.toString());
		
		close();
		return workouts;
	}
	
	//returns a list of all exercises
	public List<Exercise> getAllExercises() {
		List<Exercise> exercises = new LinkedList<Exercise>();
		
		String query = "SELECT * FROM " + EXERCISE_TABLE;
		open();
		Cursor cursor = database.rawQuery(query, null);
		
		Exercise exercise = null;
		if (cursor.moveToFirst()) {
			do {
				exercise = new Exercise();
				int id = cursor.getColumnIndex("_id");
				int name = cursor.getColumnIndex("name");
		        int details = cursor.getColumnIndex("details");
		        exercise.setID(Integer.parseInt(cursor.getString(id)));
				exercise.setName(cursor.getString(name));
				exercise.setDetails(cursor.getString(details));
		        
				//add to list
				exercises.add(exercise);
			} while (cursor.moveToNext());
		}
		
		//for debugging
		Log.d("getAllExercises()", exercises.toString());
		
		close();
		return exercises;
	}
	
	public User getUser() {
		open();
		String query = "SELECT * FROM " + USER_TABLE; //should only have one user
		Cursor cursor = database.rawQuery(query, null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		
		User user = new User();
		user.setName(cursor.getString(cursor.getColumnIndex("name")));
		user.setAge(cursor.getString(cursor.getColumnIndex("age")));
		user.setHeight(cursor.getString(cursor.getColumnIndex("height")));
		user.setWeight(cursor.getString(cursor.getColumnIndex("weight")));
		
		//not sure if necessary
		close();
		return user;
	}
	
	public Achievement getAcheivements() {
		open();
		String query = "SELECT * FROM " + ACHIEVE_TABLE; //should only have one row
		Cursor cursor = database.rawQuery(query, null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		
		Achievement achieve = new Achievement();
		achieve.setFastestTime(cursor.getString(cursor.getColumnIndex("fastest_time")));
		achieve.setNumCompleted(cursor.getString(cursor.getColumnIndex("num_completed")));
		
		//not sure if necessary
		close();
		return achieve;
	}
	
	public Goal getGoal(int id) {
		open();
		Cursor cursor = database.query(GOAL_TABLE, null, "_id="+id, null, null, null, null);
		
		if (cursor != null) {
			cursor.moveToFirst();
		}
		
		Goal goal = new Goal();
		int name = cursor.getColumnIndex("name");
        int end_date = cursor.getColumnIndex("end_date");
        int progress = cursor.getColumnIndex("progress");
        goal.setID(id);
		goal.setName(cursor.getString(name));
		goal.setEndDate(cursor.getString(end_date));
		goal.setProgress(cursor.getString(progress));
		
		//for debugging
		Log.d("getGoal("+id+")", goal.toString());
		close();
		return goal;
	}
	
	public Workout getWorkout(int id) {
		open();
		Cursor cursor = database.query(WORK_TABLE, null, "_id="+id, null, null, null, null);
		
		if (cursor != null) {
			cursor.moveToFirst();
		}
		
		Workout workout = new Workout();
		int goal_id = cursor.getColumnIndex("goal_id");
        int date = cursor.getColumnIndex("date");
        int details = cursor.getColumnIndex("details");
        int image_path = cursor.getColumnIndex("image_path");
        int audio_file = cursor.getColumnIndex("audio_file");
        workout.setID(id);
		workout.setGoalId(Integer.parseInt(cursor.getString(goal_id)));
		workout.setDate(cursor.getString(date));
		workout.setDetails(cursor.getString(details));
		workout.setImagePath(cursor.getString(image_path));
		workout.setAudioPath(cursor.getString(audio_file));
		
		//for debugging
		Log.d("getWorkout("+id+")", workout.toString());
		close();
		return workout;
	}
	
	public Exercise getExercise(int id) {
		open();
		Cursor cursor = database.query(EXERCISE_TABLE, null, "_id="+id, null, null, null, null);
		
		if (cursor != null) {
			cursor.moveToFirst();
		}
		
		Exercise exercise = new Exercise();
		int name = cursor.getColumnIndex("name");
        int details = cursor.getColumnIndex("details");
        exercise.setID(id);
		exercise.setName(cursor.getString(name));
		exercise.setDetails(cursor.getString(details));
		
		//for debugging
		Log.d("getExercise("+id+")", exercise.toString());
		close();
		return exercise;
	}
}
