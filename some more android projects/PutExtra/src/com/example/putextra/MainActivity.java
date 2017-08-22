package com.example.putextra;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {
	Button btn;
	EditText ed;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		btn=(Button)findViewById(R.id.button1);
		ed=(EditText)findViewById(R.id.editText1);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name= ed.getText().toString();
				Intent i = new Intent();
				i.setClass(MainActivity.this, Second.class);
				i.putExtra("key", name);
				startActivity(i);
				
			}
		});
		
	}
			
			
			
		
			
			
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
