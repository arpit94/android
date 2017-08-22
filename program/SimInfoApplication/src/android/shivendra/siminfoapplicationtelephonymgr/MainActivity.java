package android.shivendra.siminfoapplicationtelephonymgr;

import java.util.ArrayList;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	String phoneDetails;
	ArrayList<String> list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		list= new ArrayList<String>();
		TelephonyManager  tm=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		
		//Get IMEI Number of Phone
        String IMEINumber=tm.getDeviceId();
        list.add("IMEI NUMBER "+IMEINumber);

//Get Subscriber ID
         String subscriberID=tm.getDeviceId();
         

 //Get SIM Serial Number
           String SIMSerialNumber=tm.getSimSerialNumber();
           list.add("SIM Serial Number "+SIMSerialNumber);
//Get Network Country ISO Code
            String networkCountryISO=tm.getNetworkCountryIso();

//Get SIM Country ISO Code
              String SIMCountryISO=tm.getSimCountryIso();

//Get the device software version
              String softwareVersion=tm.getDeviceSoftwareVersion();
              list.add("Software version "+softwareVersion);
//Get the Voice mail number
              String voiceMailNumber=tm.getVoiceMailNumber();


//Get the Phone Type CDMA/GSM/NONE
           int phoneType=tm.getPhoneType();

           switch (phoneType) 
           {
                   case (TelephonyManager.PHONE_TYPE_CDMA):
                              // your code
                                  break;
                   case (TelephonyManager.PHONE_TYPE_GSM): 
                              // your code                 
                                  break;
                   case (TelephonyManager.PHONE_TYPE_NONE):
                              // your code              
                                   break;
            }

//Find whether the Phone is in Roaming, returns true if in roaming
            boolean isRoaming=tm.isNetworkRoaming();
            
             if(isRoaming)
                     phoneDetails+="\nIs In Roaming : "+"YES";
             else
                    phoneDetails+="\nIs In Roaming : "+"NO";
           

//Get the SIM state
           int SIMState=tm.getSimState();
           switch(SIMState)
           {
                   case TelephonyManager.SIM_STATE_ABSENT :
                       Toast.makeText(MainActivity.this, "No Sim Available", Toast.LENGTH_LONG).show();
                       break;
                   case TelephonyManager.SIM_STATE_NETWORK_LOCKED :
                	   Toast.makeText(MainActivity.this, "Sim card Locked by network", Toast.LENGTH_LONG).show();
                       break;
                   case TelephonyManager.SIM_STATE_PIN_REQUIRED :
                	   Toast.makeText(MainActivity.this, "Sim card Locked by User", Toast.LENGTH_LONG).show();
                       break;
                   case TelephonyManager.SIM_STATE_PUK_REQUIRED :
                	   Toast.makeText(MainActivity.this, "Sim card Locked PUK Required", Toast.LENGTH_LONG).show();
                       break;
                   case TelephonyManager.SIM_STATE_READY :
                       Toast.makeText(MainActivity.this, "Sim card Ready to work", Toast.LENGTH_LONG).show();
                       break;
                   case TelephonyManager.SIM_STATE_UNKNOWN :
                       // your code
                       break;
         
           
           }
           ListView lv = (ListView)findViewById(R.id.listView1);
           ArrayAdapter<String> adp = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,list);
           lv.setAdapter(adp);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
