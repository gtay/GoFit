package com.cmu.gofit;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class Exercises extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState); // call superclass's version
		setContentView(R.layout.gofit_exercises); // inflate the GUI
	}
}