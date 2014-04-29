package dblayout;

import android.content.ContentValues;
import android.content.Context;

public class Create extends DatabaseConnector {
	public Create() { } //empty constructor
	
	public Create(Context context) {
		// create a new DatabaseOpenHelper
	    databaseOpenHelper =  new DatabaseOpenHelper(context, DATABASE_NAME, null, 1);
	    
	    //initiate user and achievement to be updated with real values
	    open();
	    ContentValues user_values = new ContentValues();
	    user_values.put("name", "name");
	    user_values.put("age", "age");
	    user_values.put("height", "height");
	    user_values.put("weight", "weight");
	    database.insert(USER_TABLE, null, user_values);
	    
	    ContentValues achieve_values = new ContentValues();
	    achieve_values.put("num_completed", "0");
	    achieve_values.put("fastest_time", "None");
	    database.insert(USER_TABLE, null, achieve_values);
	    
	    close();
	} // end Create

}
