package com.apsmind.ddbconnect;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.TextView;

public class Activity1 extends Activity {
	TextView t1;
	Record eventsdata;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity1);
		t1=(TextView)findViewById(R.id.textView1);
		eventsdata =new Record(this);
		SQLiteDatabase db=eventsdata.getReadableDatabase();//event is an entry of database table
		Cursor cursor=db.query(Record.Table_name, null, null, null, null, null, null);
		StringBuilder stb=new StringBuilder("Saved Events :\n\n");
		while(cursor.moveToNext())
		{
			long id=cursor.getLong(0);
			String name=cursor.getString(1);
			String email=cursor.getString(2);
			String password=cursor.getString(3);
			String address=cursor.getString(4);
			 stb.append(id +": name--: " + name +",\n"+ " email--:" + email +",\n"+ " password--:" + password +",\n"+ " address--:" + address + "\n\n");
		}
			t1.setText(stb);
		
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity1, menu);
		return true;
	}

}
