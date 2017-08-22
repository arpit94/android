package com.example.controldemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

public class toggle_activity extends Activity{
	ToggleButton tb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_toggle);
		//tb = (ToggleButton)findViewById(R.id.btnToggle);	
	}
	public void onToggle(View view) {
	    // Is the toggle on?
	    boolean on = ((ToggleButton) view).isChecked();
	    tb =(ToggleButton) findViewById(view.getId());
	    String msg="";
	    if (on) {
	        msg = "ON " + tb.getTextOn().toString();
	    } else {
	        msg = "Off " + tb.getTextOff().toString();
	    }
	    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
	}
}
