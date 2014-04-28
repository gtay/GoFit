package dblayout;

import android.content.Context;

public class Create extends DatabaseConnector {
	public Create() { } //empty constructor
	
	public Create(Context context) {
		// create a new DatabaseOpenHelper
	    databaseOpenHelper =  new DatabaseOpenHelper(context, DATABASE_NAME, null, 1);
	} // end Create

}
