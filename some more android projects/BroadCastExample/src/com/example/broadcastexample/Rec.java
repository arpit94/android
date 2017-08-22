package com.example.broadcastexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.widget.Toast;

public class Rec extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(context, "UsB Connected ", Toast.LENGTH_LONG).show();
		Intent i= new Intent(context,MainActivity.class);
		 i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);
	}


}
