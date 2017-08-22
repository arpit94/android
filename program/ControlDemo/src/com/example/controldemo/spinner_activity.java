package com.example.controldemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class spinner_activity extends Activity{
	Spinner spn;
	final String listi[]={"INDIA","CHINA","USA","UK","PERIS"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spn);
		spn=(Spinner)findViewById(R.id.spn1);
		ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,listi);
		spn.setAdapter(ad);
	}

}
