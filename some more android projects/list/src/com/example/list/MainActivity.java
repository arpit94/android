package com.example.list;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ListView ls;
	String city[]={"Delhi","Mumbai","Patna","Jaipur"};
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ls=(ListView)findViewById(R.id.listView1);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,city);
        ls.setAdapter(adp);
        ls.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int pos,
					long id) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "You have clicked"+city[pos], Toast.LENGTH_LONG).show();
				
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
