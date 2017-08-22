package com.androidhive.pushnotifications;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<Contact>{
	ArrayList<Contact> list;
	Context c;
	int resid;
	Contact con;
		public CustomAdapter(Context c1,int resid1, ArrayList<Contact> list1) {
		super(c1,R.layout.row,list1);
		this.list=list1;
		this.c=c1;
		// TODO Auto-generated constructor stub
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
			LayoutInflater lif=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowview=lif.inflate(R.layout.row, parent,false);
		
		
		TextView tv=(TextView)rowview.findViewById(R.id.textView2);
		tv.setText(list.get(position).get_name());
		
		return rowview;
	}

}
