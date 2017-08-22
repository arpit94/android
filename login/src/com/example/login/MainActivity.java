package com.example.login;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView tv1,tv2;
	EditText et1 , et2;
	CheckBox cb;
	Button btn;
	String uname ;
	String upass;
	SharedPreferences spref;
	boolean access =false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv1 = (TextView)findViewById(R.id.textView1);
		tv2 = (TextView)findViewById(R.id.textView2);
		et1 = (EditText)findViewById(R.id.editText1);
		et2 = (EditText)findViewById(R.id.editText2);
		btn = (Button)findViewById(R.id.button1);
		cb = (CheckBox)findViewById(R.id.checkBox1);
		spref = (SharedPreferences)PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
	
		access= spref.getBoolean("access", false);
		if(access)
		{
			Intent i = new Intent();
			i.setClass(MainActivity.this, LogoutActivity.class);
			startActivity(i);
		}
		
		cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				access = isChecked;
			}
		});
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				uname	= spref.getString("uname", "admin");
				 upass = spref.getString("upass", "admin");
				String un = et1.getText().toString();
				String up = et2.getText().toString();
				
				
						
			if((upass.equals("admin")&&uname.equals("admin")))
			{
				SharedPreferences.Editor spe= spref.edit();
				spe.putString("uname", un);
				spe.putString("upass", up);
				spe.commit();
				Intent i = new Intent();
				i.setClass(MainActivity.this, LogoutActivity.class);
				startActivity(i);
			}
			else
			{
				if(un.equals(uname)&&up.equals(upass))
				{
					if(cb.isChecked())
					{
						SharedPreferences.Editor spe= spref.edit();
						spe.putBoolean("access", access);
						spe.commit();
					}
					Toast.makeText(MainActivity.this, "login success", Toast.LENGTH_LONG).show();
					Intent i = new Intent();
					i.setClass(MainActivity.this, LogoutActivity.class);
					startActivity(i);
				}
			}
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
