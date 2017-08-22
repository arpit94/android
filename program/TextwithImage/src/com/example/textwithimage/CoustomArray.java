package com.example.textwithimage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CoustomArray extends ArrayAdapter<String>{
	Context con;
	int vid;
	String city[];
	int imglist[];
	CoustomArray(Context c , int v, String arr[], int imgid[])
	{
		super(c, v, arr);
		
		con  = c;
		vid = v;
		city = arr;
		imglist=imgid;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		if(convertView==null){
			LayoutInflater inf = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView =inf.inflate(vid, null);
			
			ImageView iv = (ImageView)convertView.findViewById(R.id.imageView1);
			TextView tv = (TextView)convertView.findViewById(R.id.textView1);
			
			tv.setText(city[position]);
			iv.setImageResource(imglist[position]);
			
		}

		
		
		
		return convertView;
		
		
		
		
	}

}
