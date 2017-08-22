package com.example.popupmenu;

import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData.Item;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Button btnpop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnpop = (Button) findViewById(R.id.button1);
		
		btnpop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				PopupMenu popup=new PopupMenu(MainActivity.this, btnpop);
				
				popup.getMenuInflater().inflate(R.menu.main, popup.getMenu());
				
				popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {
					
					@Override
					public boolean onMenuItemClick(MenuItem arg0) {

						
						Toast.makeText(MainActivity.this, "you clicked"+arg0.getTitle(), Toast.LENGTH_LONG).show();
						return false;
					}
				});
				popup.show();
				
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
