package com.cmu.gofit;

import android.app.Activity;
import android.os.Bundle;

public class Goals extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState); // call superclass's version
		setContentView(R.layout.gofit_goals); // inflate the GUI
	}

}