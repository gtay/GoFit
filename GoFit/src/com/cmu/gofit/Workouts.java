package com.cmu.gofit;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import dblayout.Read;
import entities.Workout;

public class Workouts extends Activity {
	
	public static final String WORKOUT_ID = "WORKOUT_ID";
	private boolean create = true;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState); // call superclass's version
		setContentView(R.layout.gofit_workouts); // inflate the GUI
		
		Read dbRead = new Read();
		List<Workout> workouts = dbRead.getAllWorkouts();
		
		// create table containing buttons to all previous workouts
		TableLayout tl = (TableLayout) findViewById(R.id.workout_table);
		// 3 workouts per row
		for (int i = 0; i < workouts.size(); i = i + 3) {
			TableRow tr = new TableRow(this);
			tr.setLayoutParams(new LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			for (int j = 0; j < 3; j++) {
				int index = i+j;
				if (index >= workouts.size()) {
					break;
				}
				final Workout w = workouts.get(index);
				// add button corresponding to workout
				Button b = new Button(this);
				OnClickListener bClicked = new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(Workouts.this, WorkoutLog.class);
						intent.putExtra(Workouts.WORKOUT_ID, w.getID());
						startActivity(intent);
					}
				};
				b.setOnClickListener(bClicked);
				b.setText(w.getDate());
				tr.addView(b);
			}
			// add assembled row to tablelayout
			tl.addView(tr, new TableLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			
		}
		Button addNewButton = (Button) findViewById(R.id.wo_button1);
		addNewButton.setOnClickListener(addNewClicked);

	}
	
	@Override
	public void onResume() {
		super.onResume();
		// refresh page to reflect new entry
		if (!create) {
			Intent intent = new Intent(Workouts.this, Workouts.class);
			startActivity(intent);
			finish();
		} else {
			create = false;
		}
	}
	OnClickListener addNewClicked = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent i = new Intent(Workouts.this, LogForm.class);
			startActivity(i);
		}
	};

}