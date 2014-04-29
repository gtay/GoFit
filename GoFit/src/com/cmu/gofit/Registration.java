package com.cmu.gofit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import dblayout.Create;
import dblayout.Read;
import dblayout.Update;
import entities.User;

public class Registration extends Activity {
	
	private EditText nameEditText;
	private EditText ageEditText;
	private EditText heightEditText;
	private EditText weightEditText;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState); // call superclass's version
		setContentView(R.layout.gofit_registration); // inflate the GUI
		
		Create dbCreate = new Create(getApplicationContext());
		Read dbRead = new Read();
		
		User u = dbRead.getUser();
		
		// go to dashboard if User has already been created for this app
		if (!u.getName().equals("name")) {
			Intent intent = new Intent(Registration.this, Home.class);
			startActivity(intent);
			finish();
		// prompt user for their information
		} else {
			Button submitButton = (Button) findViewById(R.id.reg_submitbutton);
			submitButton.setOnClickListener(submitClicked);
			nameEditText = (EditText) findViewById(R.id.reg_text4);
			ageEditText = (EditText) findViewById(R.id.reg_text6);
			heightEditText = (EditText) findViewById(R.id.reg_text8);
			weightEditText = (EditText) findViewById(R.id.reg_text10);
		}
	}
	
	OnClickListener submitClicked = new OnClickListener() {
		@Override
		public void onClick(View v) {
			String name = nameEditText.getText().toString();
			String age = ageEditText.getText().toString();
			String height = heightEditText.getText().toString();
			String weight = weightEditText.getText().toString();
			
			// add new user to database
			Update dbUpdate = new Update();
			User newUser = new User();
			newUser.setName(name);
			newUser.setAge(age);
			newUser.setHeight(height);
			newUser.setWeight(weight);
			
			dbUpdate.updateUser(newUser);
			
			Intent intent = new Intent(Registration.this, Home.class);
			startActivity(intent);
			finish();
		}
	};
}