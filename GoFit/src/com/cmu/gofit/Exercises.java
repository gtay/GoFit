package com.cmu.gofit;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import dblayout.Read;
import entities.Exercise;

public class Exercises extends Activity {
	
	private List<Exercise> exercises;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState); // call superclass's version
		setContentView(R.layout.gofit_exercises); // inflate the GUI
		
		Read dbRead = new Read();
		exercises = dbRead.getAllExercises();
		
		Button addButton = (Button) findViewById(R.id.ex_button1);
		addButton.setOnClickListener(addClicked);
		
	}
	
	OnClickListener addClicked = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(Exercises.this, ExercisesForm.class);
			startActivity(intent);
		}
	};
}