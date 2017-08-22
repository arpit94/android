package com.example.controldemo;



import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity {
	Button btnRadio;
	Button btnTab;
	Button btnToggle;
	Button btnSpinner;
	Button btnchkBox;
	Button btnProgress;
	Button btnDialoButton;
	Button btnNoty, btnSwitch;
	
	int notificationID = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnRadio = (Button)findViewById(R.id.btnRadio);
		btnTab = (Button) findViewById(R.id.btnTab);
		btnToggle= (Button)findViewById(R.id.btnToggle);
		btnSpinner = (Button)findViewById(R.id.btnSpinner);
		btnchkBox = (Button)findViewById(R.id.chkBox);
		btnProgress = (Button)findViewById(R.id.btnProgress);
		btnDialoButton = (Button) findViewById(R.id.btnDialog);
		btnNoty = (Button)findViewById(R.id.btnNoti);
		btnSwitch=(Button)findViewById(R.id.btnswitch);
		btnSwitch.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent is = new Intent();
				is.setClass(getApplicationContext(), SwitchActivity.class);
				startActivity(is);
				
			}
		});
		btnRadio.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				Intent iR = new Intent();
				iR.setClass(getApplicationContext(), radio_activity.class);
				startActivity(iR);
				
			}
		});
		btnTab.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent iTab = new Intent();
				iTab.setClass(getApplicationContext(), tab_activity.class);
				startActivity(iTab);
				
			}
		});
		btnToggle.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				Intent iToggle = new Intent();
				iToggle.setClass(getApplicationContext(), toggle_activity.class);
				startActivity(iToggle);
			}
		});
		btnSpinner.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				Intent iSpinner = new Intent();
				iSpinner.setClass(getApplicationContext(),spinner_activity.class);
				startActivity(iSpinner);
				
			}
		});
		btnchkBox.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				Intent iCheck = new Intent();
				iCheck.setClass(getApplicationContext(), checkbox_activity.class);
				startActivity(iCheck);
				
			}
		});
		btnProgress.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				Intent iProgress = new Intent();
				iProgress.setClass(getApplicationContext(), progressbar_activity.class);
				startActivity(iProgress);
				
			}
		});
		
		btnDialoButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent iDialog = new Intent();
				iDialog.setClass(getApplicationContext(),dialog_activity.class);
				startActivity(iDialog);
				
			}
		});
		btnNoty.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				displayNotification();
				
			}
		});
	}
	@SuppressWarnings("deprecation")
	protected void displayNotification()
    {
        //---PendingIntent to launch activity if the user selects
        // this notification---
		//int notificationID = 1;
        Intent i = new Intent(this, NotificationView.class);
        i.putExtra("notificationID", notificationID);

        PendingIntent pendingIntent =      PendingIntent.getActivity(this, 0, i, 0);

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE); 

		Notification notif = new Notification( R.drawable.ic_launcher, "Reminder: Meeting starts in 5 minutes",
																																									System.currentTimeMillis());

        CharSequence from = "System Alarm";
        CharSequence message = "Meeting with customer at 3pm...";
        
        notif.setLatestEventInfo(this, from, message, pendingIntent);

        //---100ms delay, vibrate for 250ms, pause for 100 ms and
        // then vibrate for 500ms---
        notif.vibrate = new long[] { 100, 250, 100, 500};
        notif.defaults |= Notification.DEFAULT_SOUND;
        //notif.sound = Uri.parse("file:///sdcard/notification/notification.mp3");
        nm.notify(notificationID, notif);        
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
