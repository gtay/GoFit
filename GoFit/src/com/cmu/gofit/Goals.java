package com.cmu.gofit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import dblayout.Delete;
import dblayout.Read;
import dblayout.Update;
import entities.Achievement;
import entities.Goal;

public class Goals extends Activity {
	
	private List<Goal> goals;
	private Button addNewButton;
	private LinearLayout goalContainer;
	private boolean create = true;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState); // call superclass's version
		setContentView(R.layout.gofit_goals); // inflate the GUI
		
		goalContainer = (LinearLayout) findViewById(R.id.goal_container);
		addNewButton = (Button) findViewById(R.id.goals_button1);
		addNewButton.setOnClickListener(addNewClicked);
		
		final Read dbRead = new Read();
		goals = dbRead.getAllGoals();
		
		for (int i = 0; i < goals.size(); i++) {
			final Goal g = goals.get(i);
			LinearLayout ll = new LinearLayout(this);
			ll.setLayoutParams(new LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			ll.setOrientation(LinearLayout.VERTICAL);
			TextView nameText = new TextView(this);
			TextView deadlineText = new TextView(this);
			TextView filler = new TextView(this);
			final TextView progressText = new TextView(this);
			SeekBar progressBar = new SeekBar(this);
			filler.setPadding(0, 20, 0, 0);
			nameText.setPadding(10, 0, 0, 0);
			deadlineText.setPadding(10, 0, 0, 0);
			progressText.setPadding(10, 0, 0, 0);
			progressBar.setPadding(20, 0, 20, 0);
			nameText.setText("Goal: " + g.getName());
			deadlineText.setText("Deadline: " + g.getEndDate());
			progressText.setText("Progress: " + g.getProgress());
			progressBar.setMax(101);
			progressBar.setProgress(Integer.parseInt(g.getProgress()));
			progressBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

				@Override
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
					seekBar.setProgress(progress);
					progressText.setText("Progress: " + Integer.toString(progress));
					if (progress == 100) {
						AlertDialog.Builder builder1 = new AlertDialog.Builder(Goals.this);
						builder1.setTitle("Confirm completion");
						builder1.setMessage("Have you completed this goal?");
						builder1.setCancelable(true);
						builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								Achievement a = dbRead.getAcheivements();
								int num_completed = Integer.parseInt(a.getNumCompleted()) + 1;
								String completed = a.getFastestTime();
								if (!completed.matches("-?\\d+(\\.\\d+)?")) {
									completed = "9999";
								}
								DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
								Date today = Calendar.getInstance().getTime();
								String todayString = df.format(today);
								Date start_date = null;
								Date end_date = null;
								String start = g.getStartDate();
								
								try {
									start_date = df.parse(start);
									end_date = df.parse(todayString);
									long diff = end_date.getTime() - start_date.getTime();
									int one_day = (1000*60*60*24);
									int days = (int) diff / one_day;
									if (days < Integer.parseInt(completed)) {
										completed = Integer.toString(days);
									} else {
										completed = "None";
									}									
								} catch (Exception e) {
									e.printStackTrace();
									Log.d(start, "date is not in correct format");
								}
								if (completed.equals("9999")) {
									completed = "None";
								}
								a.setFastestTime(completed);
								a.setNumCompleted(Integer.toString(num_completed));
								Update dbUpdate = new Update();
								dbUpdate.updateAchievements(a);
								Delete dbDelete = new Delete();
								dbDelete.deleteGoalById(g.getID());
								Toast.makeText(getApplicationContext(), "Goal has been completed and removed", Toast.LENGTH_SHORT).show();
							}
						});
						builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								dialog.cancel();
							}
						});
						AlertDialog alert = builder1.create();
						alert.show();
					} else {
						Update dbUpdate = new Update();
						g.setProgress(Integer.toString(progress));
						dbUpdate.updateGoal(g);
					}
				}

				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
				}

				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {
				}
				
			});
			ll.addView(filler);
			ll.addView(nameText);
			ll.addView(deadlineText);
			ll.addView(progressText);
			ll.addView(progressBar);
			goalContainer.addView(ll);
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		// refresh page to reflect new entry
		if (!create) {
			Intent intent = new Intent(Goals.this, Goals.class);
			startActivity(intent);
			finish();
		} else {
			create = false;
		}
	}
	
	OnClickListener addNewClicked = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(Goals.this, GoalsForm.class);
			startActivity(intent);
		}
	};
}