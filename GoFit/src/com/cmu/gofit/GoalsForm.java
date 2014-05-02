package com.cmu.gofit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import dblayout.Insert;
import entities.Goal;

public class GoalsForm extends Activity {
	
	private TextView goalText;
	private TextView deadlineText;
	private Button submitButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState); // call superclass's version
		setContentView(R.layout.gofit_goalsform); // inflate the GUI
		
		goalText = (TextView) findViewById(R.id.gf_text5);
		deadlineText = (TextView) findViewById(R.id.gf_text7);
		submitButton = (Button) findViewById(R.id.gf_button1);
		
		submitButton.setOnClickListener(submitClicked);
		
	}
	
	OnClickListener submitClicked = new OnClickListener() {
		@Override 
		public void onClick(View v) {
			String goal = goalText.getText().toString();
			String deadline = deadlineText.getText().toString();
			if (goal.length() <= 0) {
				Toast.makeText(getApplicationContext(),
						"Enter a goal first!", Toast.LENGTH_SHORT).show();
				return;
			}
			if (deadline.length() <= 0) {
				Toast.makeText(getApplicationContext(),
						"Enter a deadline first!", Toast.LENGTH_SHORT).show();
			}
			Insert dbInsert = new Insert();
			Goal g = new Goal();
			g.setProgress("0");
			g.setName(goal);
			g.setEndDate(deadline);
			dbInsert.addGoal(g);
			finish();
		}
	};
}
