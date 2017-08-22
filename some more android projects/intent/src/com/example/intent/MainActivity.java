package com.example.intent;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
Button btn;
ListView lv;
String str[] = {"delhi","gurgaon","indore","pathankot","navsari"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn=(Button)findViewById(R.id.button1);
		lv=(ListView)findViewById(R.id.listView1);
		ArrayAdapter<String> adp = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,str);
		lv.setAdapter(adp);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if(arg2==0)
				{
				Intent i = new Intent();
				
				i.setClass(MainActivity.this,SecondActivity.class);
				startActivity(i);
				}
				if(arg2==1)
				{
				Intent i = new Intent();
				
				i.setClass(MainActivity.this,Third.class);
				startActivity(i);
				}
				if(arg2==2)
				{
				Intent i = new Intent();
				
				i.setClass(MainActivity.this,Fourth.class);
				startActivity(i);
				}
				if(arg2==3)
				{
				Intent i = new Intent();
				
				i.setClass(MainActivity.this,Fifth.class);
				startActivity(i);
				}
				if(arg2==4)
				{
				Intent i = new Intent();
				
				i.setClass(MainActivity.this,Sixth.class);
				startActivity(i);
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
