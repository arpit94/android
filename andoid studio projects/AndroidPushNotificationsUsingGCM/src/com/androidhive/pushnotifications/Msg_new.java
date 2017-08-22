package com.androidhive.pushnotifications;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

public class Msg_new extends Activity {
	Button btn;
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
		setContentView(R.layout.activity_msg_new);
		Bundle bundle=getIntent().getExtras();
		gcm_id=bundle.getString("gcm_id");
		adp=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
		name=bundle.getString("name");
		tv=(TextView)findViewById(R.id.textView1);
		lv=(ListView)findViewById(R.id.listView1);
		btn=(Button)findViewById(R.id.send2);
		registerReceiver(mHandleMessageReceiver2, new IntentFilter(
				DISPLAY_MESSAGE_ACTION));
		edt=(EditText)findViewById(R.id.editText1);
		tv.setText(name);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				msg=edt.getText().toString();
				new serverconnect1().execute();
				//System.out.println("hi");
				Toast.makeText(getApplicationContext(), "Msg Sent", Toast.LENGTH_SHORT).show();
				adp.add(msg);
				lv.setAdapter(adp);
				edt.setText("");
			}
		});
	
	}
	private final BroadcastReceiver mHandleMessageReceiver2 = new BroadcastReceiver() {
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
	class serverconnect1 extends AsyncTask<Void, Void, Void>
	{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
				postData();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			return null;
		}
		
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.msg_new, menu);
		return true;
	}
	public void postData() throws JSONException{  
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://androidtraning.net63.net/gcm_server_php/post.php");
        JSONObject json = new JSONObject();
 
        try {
            // JSON data:
            json.put("regid", gcm_id);
            json.put("msg", msg);
            System.out.println(json.toString());
            JSONArray postjson=new JSONArray();
            postjson.put(json);
 
            // Post the data:
            httppost.setHeader("json",json.toString());
            httppost.getParams().setParameter("jsonpost",postjson);
 
            // Execute HTTP Post Request
            System.out.print(json);
            HttpResponse response = httpclient.execute(httppost);
 
            // for JSON:
            if(response != null)
            {
                InputStream is = response.getEntity().getContent();
 
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
 
                String line = null;
                try {
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
   //             text = sb.toString();
            }
 
 //           tv.setText(text);
 
        }catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }


}


