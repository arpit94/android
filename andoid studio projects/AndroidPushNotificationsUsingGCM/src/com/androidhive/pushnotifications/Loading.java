package com.androidhive.pushnotifications;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Loading extends Activity {
Button btn;
int count;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		btn=(Button)findViewById(R.id.button1);
		SharedPreferences spref=(SharedPreferences)PreferenceManager.getDefaultSharedPreferences(Loading.this);
		count=spref.getInt("Registered", 0);
		if(count==1)
		{
			Intent i =new Intent(Loading.this,Chatlist.class);
			startActivity(i);
			finish();
		}
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(count==0)
				{
					Intent i =new Intent(Loading.this,RegisterActivity.class);
					startActivity(i);
					finish();
				}	
			}
		});
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.loading, menu);
		return true;
	}

}
