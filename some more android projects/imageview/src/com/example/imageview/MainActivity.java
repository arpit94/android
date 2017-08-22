package com.example.imageview;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {
	ListView lv;
	String [] pname = {"p1" ,"p2" ,"p3"};
	int [] imgid = {R.drawable.img1 ,R.drawable.img2,R.drawable.img3};
	String [] price = {"50" ,"10","90"};
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView)findViewById(R.id.listView1);
		CoustomAdapter cadp = new CoustomAdapter(MainActivity.this, R.layout.row, pname, imgid, price);
		lv.setAdapter(cadp);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
