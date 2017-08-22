package com.androidhive.pushnotifications;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class Record extends SQLiteOpenHelper{

	public static final String DB_name="Chatlist.db";
	public static final int DB_version=2;
	public static final String Table_name ="Info";
	public static final String Col1_gcmid = "Gcm_id";
	public static final String Col2_name ="Name";
	
	String Query;
	
	public Record(Context context) {
		super(context, DB_name, null, DB_version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Query =  "CREATE TABLE " + Table_name + "( " + BaseColumns._ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + Col1_gcmid + " TEXT UNIQUE, "
				+ Col2_name + " TEXT );";
		Log.d("Eventsdata", "onCreate"  +Query);
		db.execSQL(Query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
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
@Override
public synchronized void close() {
	// TODO Auto-generated method stub
	super.close();
}
}
