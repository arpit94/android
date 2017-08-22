package com.example.dynamiclist;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class CoustomAdapter extends ArrayAdapter<Product>{
	Context con ;
	int lay;
	ArrayList<Product> list;

	public CoustomAdapter(Context c, int layout, ArrayList<Product>plist) {
		super(c,layout,plist);
		// TODO Auto-generated constructor stub
		

		
			
			con =c;
			lay = layout;
			list = plist;

		}
			public View getView(final int pos, View v,ViewGroup vg)
			{

			if (v==null)
		{
			LayoutInflater li = (LayoutInflater)con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = li.inflate(lay, null);  
		}
		TextView tv2= (TextView)v.findViewById(R.id.textView3);
		TextView tv = (TextView)v.findViewById(R.id.textView1);
		TextView tv1 = (TextView)v.findViewById(R.id.textView2);
		Button btn = (Button)v.findViewById(R.id.button1);

			tv2.setText(""+list.get(pos).quantity);
			tv.setText(list.get(pos).name);
			tv1.setText(""+list.get(pos).price);
			btn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					remove(list.get(pos));
				}
			});
	return v;
			}

	}


