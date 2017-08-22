package com.example.dialogex;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
int i=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btn = (Button )findViewById(R.id.button1);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			//	open("Test", "Alert",MainActivity.this);
			customDialog();
				generateNotification(MainActivity.this,"hello"+i++);
			}
		});
	}
	public void open(String msg, String title,Context c){
	      AlertDialog.Builder alertDialogBuilder =  new AlertDialog.Builder(c);
	      alertDialogBuilder.setMessage(msg);
	      alertDialogBuilder.setTitle(title);
	      alertDialogBuilder.setPositiveButton("OK", 
	      new DialogInterface.OnClickListener() {
			
	         @Override
	         public void onClick(DialogInterface arg0, int arg1) {
	            Toast.makeText(MainActivity.this, "positive", Toast.LENGTH_LONG).show();
				
	         }
	      });
	      alertDialogBuilder.setNegativeButton("Cancel", 
	      new DialogInterface.OnClickListener() {
				
	         @Override
	         public void onClick(DialogInterface dialog, int which) {
	            Toast.makeText(MainActivity.this, "negative", Toast.LENGTH_SHORT).show();
			 }
	      });
		    
	      AlertDialog alertDialog = alertDialogBuilder.create();
	      alertDialog.show();
		    
	   }
	
	void customDialog()
	{
		final Dialog dialog = new Dialog(MainActivity.this);
		dialog.setContentView(R.layout.mydialog);
		dialog.setTitle("Title...");

		// set the custom dialog components - text, image and button
		TextView text = (TextView) dialog.findViewById(R.id.textView1);
		text.setText("Android custom dialog example!");
		ImageView image = (ImageView) dialog.findViewById(R.id.imageView1);
		image.setImageResource(R.drawable.ic_launcher);

		Button dialogButton = (Button) dialog.findViewById(R.id.button1);
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		dialog.show();
	}
	private  void generateNotification(Context context, String message) {
	     
        int icon = R.drawable.ic_launcher;
        long when = System.currentTimeMillis();
         
        NotificationManager notificationManager = (NotificationManager)
        		context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(icon, message, when);
         
        String title = " Test ";//context.getString(R.string.app_name);
         
        Intent notificationIntent = new Intent(context, MainActivity.class);
        // set intent so it does not start a new activity
        
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
        		Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent =
                PendingIntent.getActivity(context, 0, notificationIntent, 0);
        notification.setLatestEventInfo(context, title, message, intent);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
         
        // Play default notification sound
        notification.defaults |= Notification.DEFAULT_SOUND;
         
      //  notification.sound = Uri.parse("android.resource://com.example.gcm"
                              
       //                        + R.raw.soundonballon);
         
        // Vibrate if vibrate is enabled
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notificationManager.notify(i, notification);      
        
 
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
