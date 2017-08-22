package com.example.controldemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class radio_activity extends Activity{
	RadioGroup rgp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_radio);
		rgp =(RadioGroup) findViewById(R.id.GroupGender);
		rgp.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				RadioButton rbtn = (RadioButton)findViewById(arg1);
				Toast.makeText(getApplicationContext(), "You Have Selected " + rbtn.getText(), Toast.LENGTH_LONG).show();
				
			}
		});
	}
}
