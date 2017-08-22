package com.example.form;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends Activity {
	CheckBox cb;
	CheckBox ch;
	CheckBox ce;
	Button btn;
	EditText elang;
	RadioButton rmale , rfemale ,rother;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		cb =(CheckBox)findViewById(R.id.checkBox2);
		ch =(CheckBox)findViewById(R.id.checkBox1);
		ce =(CheckBox)findViewById(R.id.checkBox3);
		elang=(EditText)findViewById(R.id.editText1);
		btn = (Button)findViewById(R.id.button1);
		rmale =(RadioButton)findViewById(R.id.radio0);
		rfemale =(RadioButton)findViewById(R.id.radio1);
		rother =(RadioButton)findViewById(R.id.radio2);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String gen = " ";
				if(rmale.isChecked())
					gen = rmale.getText().toString()+" ";
				if(rfemale.isChecked())
					gen = rfemale.getText().toString()+" ";
				if(rother.isChecked())
					gen = rother.getText().toString()+" ";
				
				String lang = " ";
				if(ch.isChecked())
					lang = lang+ ch.getText().toString()+" ";
				if(cb.isChecked())
			
				lang = lang+ elang.getText().toString()+" ";
				if(ce.isChecked())
				lang = lang+ ce.getText().toString()+" ";
				Toast.makeText(MainActivity.this, "Langauge"+lang+ "gender "+ gen + Toast.LENGTH_LONG, 5).show();
			
				
			}
		});
		RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup1);
		rg.setOnCheckedChangeListener(new  RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				RadioButton rb = (RadioButton)findViewById(checkedId);
				Toast.makeText(MainActivity.this,rb.getText().toString() , Toast.LENGTH_LONG).show();
				
			}
		});
		cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
					if(isChecked)
					elang.setVisibility(View.VISIBLE);
				else
					elang.setVisibility(View.GONE);
						
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
