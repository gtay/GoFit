package com.cmu.gofit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import dblayout.Insert;
import entities.Exercise;

public class ExercisesForm extends Activity {
	
	private EditText nameEditText;
	private EditText weightEditText;
	private EditText setsEditText;
	private EditText repsEditText;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState); // call superclass's version
		setContentView(R.layout.gofit_exercisesform); // inflate the GUI
				
		Button submitButton = (Button) findViewById(R.id.exf_button1);
		submitButton.setOnClickListener(submitClicked);
		
		nameEditText = (EditText) findViewById(R.id.exf_text4);
		weightEditText = (EditText) findViewById(R.id.exf_text6);
		setsEditText = (EditText) findViewById(R.id.exf_text8);
		repsEditText = (EditText) findViewById(R.id.exf_text10);
	}
	
	OnClickListener submitClicked = new OnClickListener() {
		@Override
		public void onClick(View v) {
			String name = nameEditText.getText().toString();
			int weight = Integer.parseInt(weightEditText.getText().toString());
			int sets = Integer.parseInt(setsEditText.getText().toString());
			int reps = Integer.parseInt(repsEditText.getText().toString());
			
			Insert dbInsert = new Insert();
			Exercise newExercise = new Exercise();
			newExercise.setName(name);
			newExercise.setReps(reps);
			newExercise.setSets(sets);
			newExercise.setWeight(weight);
			dbInsert.addExercise(newExercise);
			
			Intent intent = new Intent(ExercisesForm.this, Exercises.class);
			startActivity(intent);
		}
	};

}
