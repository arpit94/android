package com.example.smsreceiver;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {
SharedPreferences mypref;
int count =0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mypref = (SharedPreferences)PreferenceManager.getDefaultSharedPreferences(this);
		count = mypref.getInt("Counter", 1);
		count++;
		if(count >5)
		{
			finish();
		}
		else{
		Toast.makeText(this,count+" time app start", Toast.LENGTH_SHORT).show();
		SharedPreferences.Editor editor = mypref.edit();
	       editor.putInt("Counter", count);
	        editor.commit(); // Very important
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
