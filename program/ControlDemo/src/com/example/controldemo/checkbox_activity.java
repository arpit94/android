package com.example.controldemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class checkbox_activity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chk);
	}
	public void chkOnClick(View view){
		CheckBox chk1 = (CheckBox)findViewById(view.getId());
		String msg;
		if(chk1.isChecked()){
		msg = "CHECKED " + chk1.getText().toString();
		}else{
			msg = "UNCHECKED " + chk1.getText().toString();
		}
		
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
	}

}
