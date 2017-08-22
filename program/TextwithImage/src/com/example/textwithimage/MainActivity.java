package com.example.textwithimage;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {

	ListView lv ;
	Info i ;
	int imglist[]= {R.drawable.a, R.drawable.b,R.drawable.c};
	String city [] = {" Delhi ", " Rajsthan", " Mumbai "};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv =(ListView)findViewById(R.id.listView1);
		
		CoustomArray ca = new CoustomArray(MainActivity.this, R.layout.row, city, imglist);
		lv.setAdapter(ca);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
