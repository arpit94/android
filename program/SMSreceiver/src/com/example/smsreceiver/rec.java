package com.example.smsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class rec extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		Bundle bundle = arg1.getExtras();
		Object[] object = (Object[])bundle.get("pdus");
		SmsMessage [] msg = new SmsMessage[object.length];
		
		for(int i =0;i<object.length;i++){
			msg[i] = SmsMessage.createFromPdu((byte[]) object[i]);
			
			String phone = msg[i].getDisplayOriginatingAddress();
			String message = msg[i].getDisplayMessageBody();
			
			Toast.makeText(arg0,phone+": "+message, Toast.LENGTH_SHORT).show();
		}
		
	}

}
