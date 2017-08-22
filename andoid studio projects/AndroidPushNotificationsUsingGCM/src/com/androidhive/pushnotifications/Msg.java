package com.androidhive.pushnotifications;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import static com.androidhive.pushnotifications.CommonUtilities.DISPLAY_MESSAGE_ACTION;
import static com.androidhive.pushnotifications.CommonUtilities.EXTRA_MESSAGE;
public class Msg extends Activity {
String gcm_id;
TextView tv;
EditText edt;
ArrayAdapter<String> adp;
ListView lv;
String name;
String msg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_msg);
		Bundle bundle=getIntent().getExtras();
		gcm_id=bundle.getString("gcm_id");
		adp=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
		name=bundle.getString("name");
		tv=(TextView)findViewById(R.id.textView1);
		lv=(ListView)findViewById(R.id.listView1);
		registerReceiver(mHandleMessageReceiver1, new IntentFilter(
				DISPLAY_MESSAGE_ACTION));
		edt=(EditText)findViewById(R.id.editText1);
		tv.setText(name);
		
	}
	private final BroadcastReceiver mHandleMessageReceiver1 = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String newMessage = intent.getExtras().getString(EXTRA_MESSAGE);
			// Waking up mobile if it is sleeping
			WakeLocker.acquire(getApplicationContext());
			
			/**
			 * Take appropriate action on this message
			 * depending upon your app requirement
			 * For now i am just displaying it on the screen
			 * */
			
			// Showing received message
			adp.add(newMessage);
			lv.setAdapter(adp);
			Toast.makeText(getApplicationContext(), "New Message: " + newMessage, Toast.LENGTH_LONG).show();
			
			// Releasing wake lock
			WakeLocker.release();
		}
	};
	class serverconnect extends AsyncTask<Void, Void, Void>
	{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
	try
	{ HttpClient httpclient=new DefaultHttpClient();
			HttpPost httppost=new HttpPost("http://androidtraning.net63.net/gcm_server_php/send_message.php?regId="+gcm_id+"&message="+msg);
		     HttpResponse response = httpclient.execute(httppost);
             if(response!=null)
             System.out.println("Connection Created");
             
           

   }
   catch(Exception e)
   {
    Log.i("Error",e.toString());
 
   }
			return null;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.msg, menu);
		return true;
	}
public void send(View view){
	msg=edt.getText().toString();
	new serverconnect().execute();
	//System.out.println("hi");
	Toast.makeText(getApplicationContext(), "Msg Sent", Toast.LENGTH_SHORT).show();
	adp.add(msg);
	lv.setAdapter(adp);
	edt.setText("");
}
}
