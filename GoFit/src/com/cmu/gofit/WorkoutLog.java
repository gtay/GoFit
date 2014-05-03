package com.cmu.gofit;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import dblayout.Read;
import entities.Workout;

public class WorkoutLog extends Activity {
	
	private TextView dateText;
	private TextView detailText;
	private ImageView logImage;
	private Button playButton;
	
	private MediaPlayer mPlayer;
	private Workout w;
	private int workoutId;
	private boolean firstPlay = true;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState); // call superclass's version
		setContentView(R.layout.gofit_workoutlog); // inflate the GUI
		
		dateText = (TextView) findViewById(R.id.wl_text2);
		detailText = (TextView) findViewById(R.id.wl_text3);
		logImage = (ImageView) findViewById(R.id.wl_image);
		playButton = (Button) findViewById(R.id.wl_button1);
		
		playButton.setOnClickListener(playClicked);
		
		// retrieve workout based on the ID passed from intent
		Bundle extras = getIntent().getExtras();
		workoutId = extras.getInt(Home.WORKOUT_ID);
		Read dbRead = new Read();
		Log.d("user", Integer.toString(workoutId));
		w = dbRead.getWorkout(workoutId);
		dateText.setText("Daily Workout Log - " + w.getDate());
		detailText.setText(w.getDetails());
		
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
	OnClickListener playClicked = new OnClickListener() {
		@Override
		public void onClick(View v) {
            if (firstPlay) {
            	startPlaying();
            } else {
            	stopPlaying();
            	startPlaying();
            }
		}
	};
	
    private void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
    }

    private void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(w.getAudioPath());
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
        }
    }

}