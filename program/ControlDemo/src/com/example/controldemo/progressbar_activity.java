package com.example.controldemo;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class progressbar_activity extends Activity{
	
	ProgressBar prg1;
	Button btnAdd;
	Button btnMin;
	public static int pstatus=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progressbar_activity);
		//prg1 = (ProgressBar)findViewById(R.id.prBar1);
		prg1 = (ProgressBar) findViewById(R.id.prBar1);
		
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnMin = (Button)findViewById(R.id.btnMin);
        btnAdd.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				if(pstatus<100 ){pstatus=pstatus+10;}
				prg1.setProgress(pstatus);
				
			}
		});
    	btnMin.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				if(pstatus>=10 ){pstatus=pstatus-10;}
				prg1.setProgress(pstatus);
				
			}
		});
	}

}
