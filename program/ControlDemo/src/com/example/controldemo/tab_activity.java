package com.example.controldemo;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class tab_activity extends TabActivity {
	TabHost tabh;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab);
		Intent i = new Intent(this,radio_activity.class);
		Intent i2 = new Intent(this,tab_activity.class);

		tabh = getTabHost();
		Resources rs = getResources();
		tabh.addTab(tabh.newTabSpec("First").setIndicator("Radio",rs.getDrawable(R.drawable.ic_launcher)).setContent(i));
		tabh.addTab(tabh.newTabSpec("Second").setIndicator("Main",rs.getDrawable(R.drawable.house_2)).setContent(i2));
		tabh.addTab(tabh.newTabSpec("Second").setIndicator("Main",rs.getDrawable(R.drawable.doctor)).setContent(i2));
		
		
	}
}
