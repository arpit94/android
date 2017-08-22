package com.example.imageview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CoustomAdapter extends ArrayAdapter<String>{
	Context con ;
	int lay;
	String []productlist;
	String []pricelist;
	int imgid[];

	public CoustomAdapter(Context c, int layout, String [] product, int [] imgids, String [] price) {
		super(c,layout,product);
		// TODO Auto-generated constructor stub
		

		
			
			con =c;
			lay = layout;
			productlist= product;
			pricelist=price;
			imgid= imgids;

		}
			public View getView(int pos, View v,ViewGroup vg)
			{

			if (v==null)
		{
			LayoutInflater li = (LayoutInflater)con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = li.inflate(lay, null);  
		}
		ImageView iv = (ImageView)v.findViewById(R.id.imageView1);
		TextView tv = (TextView)v.findViewById(R.id.textView1);
		TextView tv1 = (TextView)v.findViewById(R.id.textView2);

			iv.setImageResource(imgid[pos]);
			tv.setText(productlist[pos]);
			tv1.setText(pricelist[pos]);
	return v;
			}

	}


