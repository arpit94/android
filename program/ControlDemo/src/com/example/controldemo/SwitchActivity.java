package com.example.controldemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class SwitchActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        
        final Switch sw=(Switch)findViewById(R.id.switch1);
        sw.setOnClickListener(new OnClickListener()
        {
			
			public void onClick(View v)
			{
				
			    String msg="";
			   
				if (sw.isChecked()) 
				{
			        msg = "Hi " + sw.getTextOn().toString();
			    } else {
			        msg = "Bye " + sw.getTextOff().toString();
			    }
			    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
			}
		
				// TODO Auto-generated method stub
				
			
		});
    }
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_switch, menu);
		return true;
	}

   
}
