package com.example.controldemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class dialog_activity extends Activity{
	Button btnCon;
	Button btnNoti;
	Button btnPro;
	AlertDialog.Builder bl;
	ProgressDialog pg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog);
		btnCon = (Button)findViewById(R.id.btnConfirm);
		btnNoti = (Button)findViewById(R.id.btnNotify);
		btnPro = (Button)findViewById(R.id.btnProgress);
		bl = new AlertDialog.Builder(this);
		btnCon.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				//AlertDialog.Builder bl = new AlertDialog.Builder(this);
				bl.setTitle("GOTO Main").setMessage("ARE YOU SURE...!").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						Intent i = new Intent();
						i.setClass(getApplicationContext(), MainActivity.class);
						startActivity(i);
						
					}
				}).setNegativeButton("NO", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						//dialog.cancel();
						
					}
				});
				AlertDialog alert11 = bl.create();
				Toast.makeText(getApplicationContext(), "TEST", Toast.LENGTH_SHORT).show();
	            alert11.show();
			}
		});
		btnNoti.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				final String color[]={"RED","GREEN","YELLOW","PINK"};
				bl.setTitle("TEST COLOR")
		           .setItems(color, new DialogInterface.OnClickListener() {
		               public void onClick(DialogInterface dialog, int which) {
		            	   Toast.makeText(getApplicationContext(), color[which], Toast.LENGTH_SHORT).show();
		           }
		    });
				AlertDialog alert11 = bl.create();
				
	            alert11.show();
			}
		});
		btnPro.setOnClickListener(new OnClickListener() {
	
			public void onClick(View v) {
				
				pg = ProgressDialog.show(dialog_activity.this, "Progress...", "Test Message");
				new Thread() {

					public void run() {

					try{

					sleep(10000);

					} catch (Exception e) {
					}
					pg.dismiss();

					}

					}.start();
		
			}
		});
	}
}
