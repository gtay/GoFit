package com.cmu.gofit;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LogForm extends Activity {
	
	public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	public static final int MEDIA_TYPE_IMAGE = 1;
	private Uri photoUri;
	private String todayString;
	private static String imagePath;
	private static String timeStamp;
	
    private static final String LOG_TAG = "AudioRecordTest";
    private static String mFileName = null;
    
    private Button addAudioButton;
    private boolean mStartRecording = true;
    private MediaRecorder mRecorder = null;

	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState); // call superclass's version
		setContentView(R.layout.gofit_logform); // inflate the GUI
		
		Button addPhotoButton = (Button) findViewById(R.id.lf_button1);
		addPhotoButton.setOnClickListener(addPhotoClicked);
		addAudioButton = (Button) findViewById(R.id.lf_button2);
		addAudioButton.setOnClickListener(addAudioClicked);
		// get today's date and convert to string
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date today = Calendar.getInstance().getTime();
		todayString = df.format(today);
		
		TextView dateLabel = (TextView) findViewById(R.id.lf_text2);
		dateLabel.setText(todayString);
	}
	
	OnClickListener addPhotoClicked = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			photoUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
			camera.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
			startActivityForResult(camera, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
		}
	};
	
	OnClickListener addAudioClicked = new OnClickListener() {
		@Override
		public void onClick(View v) {
			onRecord(mStartRecording);
			if (mStartRecording) {
				addAudioButton.setText("Stop recording");
			} else {
				addAudioButton.setText("Done recording!");
				addAudioButton.setEnabled(false);
			}
			mStartRecording = !mStartRecording;
		}
	};
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
	        if (resultCode == RESULT_OK) {
	            // Image captured and saved to fileUri specified in the Intent
	            Toast.makeText(this, "Image saved to:\n" +
	                     imagePath, Toast.LENGTH_LONG).show();
	        } else if (resultCode == RESULT_CANCELED) {
	            // User cancelled the image capture
	        } else {
	            // Image capture failed, advise user
	        }
	    }
	}
	
	/** Create a file Uri for saving an image or video */
	private static Uri getOutputMediaFileUri(int type){
	      return Uri.fromFile(getOutputMediaFile(type));
	}

	/** Create a File for saving an image or video */
	private static File getOutputMediaFile(int type) {

	    File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	              Environment.DIRECTORY_PICTURES), "GoFit");

	    // Create the storage directory if it does not exist
	    if (! mediaStorageDir.exists()){
	        if (! mediaStorageDir.mkdirs()){
	            Log.d("GoFit", "failed to create directory");
	            return null;
	        }
	    }

	    // Create a media file name
	    timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    File mediaFile;
	    if (type == MEDIA_TYPE_IMAGE){
	    	imagePath = mediaStorageDir.getPath() + File.separator +
	    	        "IMG_"+ timeStamp + ".jpg";
	        mediaFile = new File(imagePath);
	    } else {
	        return null;
	    }

	    return mediaFile;
	}
	
    private void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void startRecording() {
	    File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	              Environment.DIRECTORY_MUSIC), "GoFit");

	    // Create the storage directory if it does not exist
	    if (! mediaStorageDir.exists()){
	        if (! mediaStorageDir.mkdirs()){
	            Log.d("GoFit", "failed to create directory");
	            return;
	        }
	    }
    	
	    timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    	mFileName = Environment.getExternalStorageDirectory().getAbsolutePath()
    			+ File.separator + "Music" + File.separator +
    			"GoFit" + File.separator + timeStamp + ".3gp";
    	Log.d("User", mFileName);
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

}