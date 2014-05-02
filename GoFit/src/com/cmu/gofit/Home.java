package com.cmu.gofit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import dblayout.Read;
import entities.Achievement;
import entities.User;

public class Home extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState); // call superclass's version
		setContentView(R.layout.gofit_home); // inflate the GUI

		Button exercisesButton = (Button) findViewById(R.id.home_button1);
		exercisesButton.setOnClickListener(exercisesClicked);		
		Button workoutsButton = (Button) findViewById(R.id.home_button2);
		workoutsButton.setOnClickListener(workoutsClicked);		
		Button goalsButton = (Button) findViewById(R.id.home_button3);
		goalsButton.setOnClickListener(goalsClicked);		
		Button achievementsButton = (Button) findViewById(R.id.home_button4);
		achievementsButton.setOnClickListener(achievementsClicked);

		Read dbRead = new Read();
		User u = dbRead.getUser();
		String userName = u.getName();
		TextView welcomeMessage = (TextView) findViewById(R.id.home_text1);
		welcomeMessage.setText("Welcome, "+userName+"!");
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