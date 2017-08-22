package com.example.wifiscanner;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

   WifiManager mainWifiObj;
   WifiScanReceiver wifiReciever;
   ListView list;
   String wifis[];
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      list = (ListView)findViewById(R.id.listView1);
      mainWifiObj = (WifiManager) getSystemService(Context.WIFI_SERVICE);
      wifiReciever = new WifiScanReceiver();
     
      Button btn = (Button)findViewById(R.id.button1);
      btn.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			 mainWifiObj.startScan();
			
		}
	});
      
   }


   protected void onPause() {
      unregisterReceiver(wifiReciever);
      super.onPause();
   }

   protected void onResume() {
      registerReceiver(wifiReciever, new IntentFilter(
      WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
      super.onResume();
   }

   class WifiScanReceiver extends BroadcastReceiver {
      @SuppressLint("UseValueOf")
      public void onReceive(Context c, Intent intent) {
       //  List<WifiConfiguration> wifiScanList = mainWifiObj.getConfiguredNetworks();
    	  List<ScanResult> wifiScanList = mainWifiObj.getScanResults();
          
         wifis = new String[wifiScanList.size()];
         for(int i = 0; i < wifiScanList.size(); i++){
        	 String networkname = String.format("%s", (wifiScanList.get(i).SSID));
            wifis[i] = (networkname);
         }

         list.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
         R.layout.row,wifis));
      }
   }

}