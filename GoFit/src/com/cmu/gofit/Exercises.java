package com.cmu.gofit;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import dblayout.Read;
import entities.Exercise;

public class Exercises extends Activity {
	
	private List<Exercise> exercises;
	private boolean create = true;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState); // call superclass's version
		setContentView(R.layout.gofit_exercises); // inflate the GUI
		
		Read dbRead = new Read();
		exercises = dbRead.getAllExercises();
		
		Button addButton = (Button) findViewById(R.id.ex_button1);
		addButton.setOnClickListener(addClicked);
		
		// create table that will display exercises
		TableLayout tl = (TableLayout) findViewById(R.id.ex_table);
		TableRow trHead = new TableRow(this);
		trHead.setBackgroundColor(Color.WHITE);
			trHead.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		TextView nameLabel = new TextView(this);
		TextView weightLabel = new TextView(this);
		TextView repsLabel = new TextView(this);
		TextView setsLabel = new TextView(this);
		nameLabel.setText("Name");
		weightLabel.setText("Weight (lbs.)");
		repsLabel.setText("Reps");
		setsLabel.setText("Sets");
		nameLabel.setPadding(8, 8, 8, 8);
		weightLabel.setPadding(8, 8, 8, 8);
		repsLabel.setPadding(8, 8, 8, 8);
		setsLabel.setPadding(8, 8, 8, 8);
		
		// add row header
		trHead.addView(nameLabel);
		trHead.addView(weightLabel);
		trHead.addView(setsLabel);
		trHead.addView(repsLabel);
		tl.addView(trHead, new TableLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.MATCH_PARENT));

		// add rows of data
		int count = 0;
		for (int i = 0; i < exercises.size(); i++) {
			Exercise e = exercises.get(i);
			TableRow tr = new TableRow(this);
			if (count % 2 != 1) {
				tr.setBackgroundColor(Color.LTGRAY);
			} else {
				tr.setBackgroundColor(Color.WHITE);
			}
			tr.setLayoutParams(new LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			TextView nameData = new TextView(this);
			TextView weightData = new TextView(this);
			TextView setsData = new TextView(this);
			TextView repsData = new TextView(this);
			
			nameData.setPadding(8, 8, 8, 8);
			weightData.setPadding(8, 8, 8, 8);
			setsData.setPadding(8, 8, 8, 8);
			repsData.setPadding(8, 8, 8, 8);
			
			nameData.setText(e.getName());
			weightData.setText(Integer.toString(e.getWeight()));
			setsData.setText(Integer.toString(e.getSets()));
			repsData.setText(Integer.toString(e.getReps()));
			
			if (count % 2 != 1) {
				nameData.setTextColor(Color.WHITE);
				weightData.setTextColor(Color.WHITE);
				setsData.setTextColor(Color.WHITE);
				repsData.setTextColor(Color.WHITE);
			}
			
			tr.addView(nameData);
			tr.addView(weightData);
			tr.addView(setsData);
			tr.addView(repsData);
			
			tl.addView(tr, new TableLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			count++;
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		// refresh page to reflect new entry
		if (!create) {
			Intent intent = new Intent(Exercises.this, Exercises.class);
			startActivity(intent);
			finish();
		} else {
			create = false;
		}
	}
	
	OnClickListener addClicked = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(Exercises.this, ExercisesForm.class);
			startActivity(intent);
		}
	};
}