package com.cmu.gofit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import dblayout.Insert;
import entities.Exercise;

public class ExercisesForm extends Activity {
	
	private EditText nameEditText;
	private EditText weightEditText;
	private EditText setsEditText;
	private EditText repsEditText;
	private Toast mToast;
	
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
		
		mToast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);

	}
	
	OnClickListener submitClicked = new OnClickListener() {
		@Override
		public void onClick(View v) {
			String name = nameEditText.getText().toString();
			String weight = weightEditText.getText().toString();
			String sets = setsEditText.getText().toString();
			String reps = repsEditText.getText().toString();
			
			if (name.length() == 0 || weight.length() == 0 ||
					sets.length() == 0 || reps.length() == 0) {
				// first cancel previous toast message to prevent queue buildup
				mToast.cancel();
			    mToast = Toast.makeText(getApplicationContext(), 
			    		"Form is incomplete!", Toast.LENGTH_SHORT);
			    mToast.show();
			} else {
				
			
				Insert dbInsert = new Insert();
				Exercise newExercise = new Exercise();
				newExercise.setName(name);
				newExercise.setReps(Integer.parseInt(reps));
				newExercise.setSets(Integer.parseInt(sets));
				newExercise.setWeight(Integer.parseInt(weight));
				dbInsert.addExercise(newExercise);
				
				finish();
			}
		}
	};

}
