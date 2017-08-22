package com.example2.arpitaggarwal.notificationdialog;

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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
    Button bt;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt=(Button)findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //open("hello","hi",MainActivity.this);
            customDialog();
                generateNotification(MainActivity.this,"hello"+i++);

            }
        });
    }
public void open(String msg,String title,Context c) {
    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(c);
    alertDialogBuilder.setMessage(msg);
    alertDialogBuilder.setTitle(title);
    alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(MainActivity.this, "positive", Toast.LENGTH_SHORT).show();
        }
    });
    alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(MainActivity.this, "Negative", Toast.LENGTH_SHORT).show();
        }
    });
    AlertDialog alertDialog=alertDialogBuilder.create();
    alertDialog.show();
    }
    void customDialog()
    {
        final Dialog dialog=new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Title...");
        TextView textView =(TextView) dialog.findViewById(R.id.textView);
        textView.setText("Andoid custom dialog example!");
        ImageView img=(ImageView)dialog.findViewById(R.id.imageView);
        img.setImageResource(R.drawable.ic_launcher);
        Button dialogbutton=(Button) dialog.findViewById(R.id.button2);
        dialogbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
private void generateNotification(Context context,String message){
    int icon=R.drawable.ic_launcher;
    long when=System.currentTimeMillis();
    NotificationManager notificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    Notification notification=new Notification(icon,message,when+10000);
    String Title=" Test ";
    Intent notificationIntent=new Intent(context,MainActivity.class);
    notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
    PendingIntent intent=PendingIntent.getActivity(context,0,notificationIntent,0);
    notification.setLatestEventInfo(context,Title,message,intent);
    notification.flags |=Notification.FLAG_AUTO_CANCEL;
    //Play Default notification sound
    notification.defaults |=Notification.DEFAULT_SOUND;
    //notification.sound=Uri.parse("android.resource://com.......+R.raw.soundballon);
    //Vibrate if vibrate is enabled
    notification.defaults |=Notification.DEFAULT_VIBRATE;
    notificationManager.notify(i,notification);
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
