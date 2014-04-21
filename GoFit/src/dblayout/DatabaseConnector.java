package dblayout;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public abstract class DatabaseConnector {

	 // database name
	   protected static final String DATABASE_NAME = "GoFit";
	   protected static SQLiteDatabase database; // database object
	   protected static DatabaseOpenHelper databaseOpenHelper; // database helper
	   
	// open the database connection
	   public void open() throws SQLException 
	   {
	      // create or open a database for reading/writing
	      database = databaseOpenHelper.getWritableDatabase();
	   } // end method open

	   // close the database connection
	   public void close() 
	   {
	      if (database != null)
	         database.close(); // close the database connection
	   } // end method close
	   
	   protected class DatabaseOpenHelper extends SQLiteOpenHelper 
	   {
	      // public constructor
	      public DatabaseOpenHelper(Context context, String name,
	         CursorFactory factory, int version) 
	      {
	         super(context, name, factory, version);
	      } // end DatabaseOpenHelper constructor

	      // creates the contacts table when the database is created
	      @Override
	      public void onCreate(SQLiteDatabase db) 
	      {
	         // query to create a new table named user
	         String createUser = "CREATE TABLE user" +
	            "(_id integer primary key autoincrement," +
	            "name TEXT, age TEXT, height TEXT," +
	            "weight TEXT);";
	         
	         String createGoal = "CREATE TABLE goal" +
	 	            "(_id integer primary key autoincrement," +
	 	            "name TEXT, end_date TEXT, progress TEXT);";
	         
	         //have to figure out how to get paths
	         String createWorkout = "CREATE TABLE workout" +
	 	            "(_id integer primary key autoincrement," +
	 	            "goal_id integer, date TEXT, details TEXT," +
	 	            "image_path TEXT, audio_file TEXT);";
	         
	         String createAchievement = "CREATE TABLE achievement" +
	 	            "(num_completed integer, fastest_time TEXT);";
	         
	         String createExercise = "CREATE TABLE exercise" +
	 	            "(_id integer primary key autoincrement," +
	 	            "name TEXT, details TEXT);";
	         
	         db.execSQL(createUser);
	         db.execSQL(createGoal);
	         db.execSQL(createWorkout);
	         db.execSQL(createAchievement);
	         db.execSQL(createExercise);
	         
	      } // end method onCreate

	      @Override
	      public void onUpgrade(SQLiteDatabase db, int oldVersion, 
	          int newVersion) {
	    	  //drop older table if existed
	    	  db.execSQL("DROP TABLE IF EXISTS GoFit");
	    	  this.onCreate(db);
	      } // end method onUpgrade
	   } // end class DatabaseOpenHelper
}
