package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class Rec extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		try{
			Toast.makeText(context, "Message Received", Toast.LENGTH_LONG).show();
			Bundle bundle = intent.getExtras();
			Object[] object = (Object[])bundle.get("pdus");
			SmsMessage [] msg = new SmsMessage[object.length];
			
			for(int i =0; i<object.length; i++)
			
			{
				msg[i] =SmsMessage.createFromPdu((byte [])object[i]);
				String phone = msg[i].getDisplayOriginatingAddress();
				String message=msg[i].getDisplayMessageBody();
				Toast.makeText(context, phone+":"+message, Toast.LENGTH_SHORT).show();
				
				
			}
				
		}
		catch(Exception e)
		{
			Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
		}
		
	}

}
