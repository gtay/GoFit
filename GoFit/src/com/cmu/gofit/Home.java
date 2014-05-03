package com.cmu.gofit;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import dblayout.Read;
import entities.Goal;
import entities.User;
import entities.Workout;

public class Home extends Activity {
	
	private static final int MAX_NUM_GOALS = 3;
	private static final int MAX_NUM_WORKOUTS = 6;
	
	TextView welcomeMessage;
	LinearLayout goalsContainer;
	private boolean create = true;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState); // call superclass's version
		setContentView(R.layout.gofit_home); // inflate the GUI
		
		goalsContainer = (LinearLayout) findViewById(R.id.home_goals);

		Button exercisesButton = (Button) findViewById(R.id.home_button1);
		exercisesButton.setOnClickListener(exercisesClicked);		
		Button workoutsButton = (Button) findViewById(R.id.home_button2);
		workoutsButton.setOnClickListener(workoutsClicked);		
		Button goalsButton = (Button) findViewById(R.id.home_button3);
		goalsButton.setOnClickListener(goalsClicked);		
		Button achievementsButton = (Button) findViewById(R.id.home_button4);
		achievementsButton.setOnClickListener(achievementsClicked);

		// Retrieve data
		Read dbRead = new Read();
		User u = dbRead.getUser();
		List<Workout> workouts = dbRead.getAllWorkouts();
		List<Goal> goals = dbRead.getAllGoals();
		
		// Set welcome message
		String userName = u.getName();
		welcomeMessage = (TextView) findViewById(R.id.home_text1);
		welcomeMessage.setText("Welcome, "+userName+"!");
		
		// Set recent goals
		int count = 0;
		for (int i = goals.size() - 1; i >= 0; i--) {
			if (count >= MAX_NUM_GOALS) {
				break;
			} else {
				TextView goalText = new TextView(this);
				SeekBar goalBar = new SeekBar(this);
				goalText.setText(goals.get(i).getName());
				goalText.setPadding(10, 20, 0, 0);
				goalBar.setMax(101);
				goalBar.setProgress(Integer.parseInt((goals.get(i).getProgress())));
				goalBar.setEnabled(false);
				goalBar.setPadding(15, 5, 15, 0);
				goalsContainer.addView(goalText);
				goalsContainer.addView(goalBar);
			}
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		// refresh page to reflect new entry
		if (!create) {
			Intent intent = new Intent(Home.this, Home.class);
			startActivity(intent);
			finish();
		} else {
			create = false;
		}
	}
	
	OnClickListener exercisesClicked = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(Home.this, Exercises.class);
			startActivity(intent);
		}
	};
	
	OnClickListener workoutsClicked = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(Home.this, Workouts.class);
			startActivity(intent);
		}
	};
	
	OnClickListener goalsClicked = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(Home.this, Goals.class);
			startActivity(intent);
		}
	};
	
	OnClickListener achievementsClicked = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(Home.this, Achievements.class);
			startActivity(intent);
		}
	};

}