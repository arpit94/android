package com.apsmind.ddbconnect;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import android.util.Log;

public class Record extends SQLiteOpenHelper
{


	public static final String DB_name = "Student.db";
	public static final int DB_version =2;
	public static final String Table_name ="Info";
	public static final String Col1_name = "Name";
	public static final String Col2_email ="Email";
	public static final String Col3_password ="Password";
	public static final String Col4_address = "Address";
	
	String query;
	
	public Record(Context context) 
	{
		super(context, DB_name, null, DB_version);/*null being cursor position*/
	}
	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		query =  "CREATE TABLE " + Table_name + "( " + BaseColumns._ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + Col1_name + " TEXT, "
				+ Col2_email + " TEXT," +Col3_password+ " not null," +Col4_address+ " text not null );";
		
		Log.d("Eventsdata", "onCreate"  +query);
		db.execSQL(query);
		
	}
	
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		{
			if (oldVersion >= newVersion)
				return;/*code to apply if database is sent from one version to another*/

			String sql = null;
			if (oldVersion == 1) 
				sql = "alter table " + Table_name + " add note text;";
			if (oldVersion == 2)
				sql = "";

			Log.d("EventsData", "onUpgrade	: " + sql);
			if (sql != null)
				db.execSQL(sql);
		}
		
		
	}

	public void close()
	{
		
	}
	
}
