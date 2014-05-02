package com.cmu.gofit;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import dblayout.Read;
import entities.Workout;

public class WorkoutLog extends Activity {
	
	private TextView dateText;
	private TextView detailText;
	private ImageView logImage;
	
	private int workoutId;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState); // call superclass's version
		setContentView(R.layout.gofit_workoutlog); // inflate the GUI
		
		dateText = (TextView) findViewById(R.id.wl_text2);
		logImage = (ImageView) findViewById(R.id.wl_image);
		
		// retrieve workout based on the ID passed from intent
		Bundle extras = getIntent().getExtras();
		workoutId = extras.getInt(Workouts.WORKOUT_ID);
		Read dbRead = new Read();
		Workout w = dbRead.getWorkout(workoutId);
		dateText.setText("Daily Workout Log - " + w.getDate());
		
		// set image
		File imgFile = new File(w.getImagePath());
		if (imgFile.exists()) {
		    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
		    // rotate image to correct orientation
		    try {
				ExifInterface exif = new ExifInterface(w.getImagePath());
				int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
				Matrix m = new Matrix();
				if (orientation == 3) m.postRotate(180);
				if (orientation == 6) m.postRotate(90);
				if (orientation == 8) m.postRotate(270);
				Bitmap rotatedBitmap = Bitmap.createBitmap(
						myBitmap, 0, 0, myBitmap.getWidth(), myBitmap.getHeight(), m, true);
			    logImage.setImageBitmap(rotatedBitmap);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}