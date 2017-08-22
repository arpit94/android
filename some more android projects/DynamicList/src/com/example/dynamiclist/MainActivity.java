package com.example.dynamiclist;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends Activity {
Spinner sp ;
EditText edt;
Button btnadd, btnpay;
ListView lv;
String parr[]={"Nokia", "Samsung", " Micromax "};
double pric[]={1000, 1200, 1500};
ArrayList<Product> al;
Product p ;
double  pr;
CoustomAdapter cadp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sp = (Spinner)findViewById(R.id.spinner1);
		edt= (EditText)findViewById(R.id.editText1);
		btnadd = (Button)findViewById(R.id.button1);
		btnpay = (Button)findViewById(R.id.button2);
		lv = (ListView)findViewById(R.id.listView1);
		al= new ArrayList<Product>();
		
		
		ArrayAdapter<String> adp  = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,parr);
		cadp = new CoustomAdapter(MainActivity.this, R.layout.row,al);
		
		sp.setAdapter(adp);
		lv.setAdapter(cadp);
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				pr = pric[arg2];
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		btnadd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			p  =new Product();
			p.setName(sp.getSelectedItem().toString());
			int q  = Integer.parseInt(edt.getText().toString());
			p.setPrice(pr*q);
			p.setQuantity(q);
			cadp.add(p);
			lv.smoothScrollToPosition(cadp.getCount());
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
