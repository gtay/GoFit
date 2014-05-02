package com.cmu.gofit;

import dblayout.Read;
import entities.Achievement;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Achievements extends Activity {
	
	private boolean create = true;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState); // call superclass's version
		setContentView(R.layout.gofit_achievements); // inflate the GUI
		
		Read dbRead = new Read();
		Achievement a = dbRead.getAcheivements();
		String num_completed = a.getNumCompleted();
		String fastest_time = a.getFastestTime();
		if (num_completed.equals("0")) {
			fastest_time = "You haven't completed any goals yet!";
		}
		TextView completed = (TextView) findViewById(R.id.num_completed);
		completed.setText(num_completed);
		TextView fastest = (TextView) findViewById(R.id.fastest_time);
		fastest.setText(fastest_time);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		// refresh page to reflect new entry
		if (!create) {
			Intent intent = new Intent(Achievements.this, Achievements.class);
			startActivity(intent);
			finish();
		} else {
			create = false;
		}
	}

}

