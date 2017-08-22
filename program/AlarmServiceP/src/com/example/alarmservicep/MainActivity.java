package com.example.alarmservicep;


import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {
Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn = (Button)findViewById(R.id.button1);
		
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				////***********************************************///////
				AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);		
			Calendar cal = Calendar.getInstance();
		/*	cal.add(Calendar.HOUR,1);
			cal.add(Calendar.MINUTE,5);*/
			cal.add(Calendar.SECOND,5);
			
			Intent i = new  Intent(arg0.getContext(),MainActivity2.class);
			
			PendingIntent pending = PendingIntent.getActivity
					(MainActivity.this,0,i,PendingIntent.FLAG_CANCEL_CURRENT);
			
		
			
			am.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),pending);
			
		////******************************************///////
				
			}
		});
		
		
	}

	
}
