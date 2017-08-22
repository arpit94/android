package com.androidhive.pushnotifications;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import static com.androidhive.pushnotifications.CommonUtilities.DISPLAY_MESSAGE_ACTION;
import static com.androidhive.pushnotifications.CommonUtilities.EXTRA_MESSAGE;

public class ChatActivity extends Activity {
	 private EditText messageET;
	    private ListView messagesContainer;
	    private Button sendBtn;
	    private ChatAdapter adapter;
	    String gcm_id;
	    String MSG;
	    String name;
	    private ArrayList<ChatMessage> chatHistory;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		getActionBar().setHomeButtonEnabled(true);
		 getActionBar().setDisplayHomeAsUpEnabled(true);
		Bundle bundle=getIntent().getExtras();
		gcm_id=bundle.getString("gcm_id");
		name=bundle.getString("name");
		setTitle(name);
		registerReceiver(mHandleMessageReceiver3, new IntentFilter(
				DISPLAY_MESSAGE_ACTION));
		initControls();
	}
	private final BroadcastReceiver mHandleMessageReceiver3 = new BroadcastReceiver() {
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
			 chatHistory = new ArrayList<ChatMessage>();
			 	
		        ChatMessage msg = new ChatMessage();
		        msg.setId(1);
		        msg.setMe(false);
		        msg.setMessage(newMessage);
		        msg.setDate(DateFormat.getDateTimeInstance().format(new Date()));
		        chatHistory.add(msg);
		        try {
		            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
		            r.play();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        
		                for(int i=0; i<chatHistory.size(); i++) {
		                    ChatMessage message = chatHistory.get(i);
		                    displayMessage(message);
		                }
			//Toast.makeText(getApplicationContext(), "New Message: " + newMessage, Toast.LENGTH_LONG).show();
			
			// Releasing wake lock
			WakeLocker.release();
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chat, menu);
		return true;
	}
	class serverconnect2 extends AsyncTask<Void, Void, Void>
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
	public void postData() throws JSONException{  
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://androidtraning.net63.net/gcm_server_php/post.php");
        JSONObject json = new JSONObject();
 
        try {
            // JSON data:
            json.put("regid", gcm_id);
            json.put("msg", MSG);
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

	private void initControls() {
        messagesContainer = (ListView) findViewById(R.id.messagesContainer);
        messageET = (EditText) findViewById(R.id.messageEdit);
        sendBtn = (Button) findViewById(R.id.chatSendButton);

        //TextView meLabel = (TextView) findViewById(R.id.meLbl);
       // TextView companionLabel = (TextView) findViewById(R.id.friendLabel);
        RelativeLayout container = (RelativeLayout) findViewById(R.id.container);
      //  companionLabel.setText(name);
        loadDummyHistory();


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

				MSG=messageET.getText().toString();
				String messageText=MSG;
            	
				if (TextUtils.isEmpty(messageText)) {
                    return;
                }
				new serverconnect2().execute();
				//System.out.println("hi");
				Toast.makeText(getApplicationContext(), "Msg Sent", Toast.LENGTH_SHORT).show();
				

                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setId(122);//dummy
                chatMessage.setMessage(messageText);
                chatMessage.setDate(DateFormat.getDateTimeInstance().format(new Date()));
                chatMessage.setMe(true);

                messageET.setText("");

                displayMessage(chatMessage);
            }
        });


    }

    public void displayMessage(ChatMessage message) {
        adapter.add(message);
        adapter.notifyDataSetChanged();
        scroll();
    }
    private void loadDummyHistory(){

       chatHistory = new ArrayList<ChatMessage>();

        ChatMessage msg = new ChatMessage();
        msg.setId(1);
        msg.setMe(false);
        msg.setMessage("Hi");
        msg.setDate(DateFormat.getDateTimeInstance().format(new Date()));
        chatHistory.add(msg);
        

        adapter = new ChatAdapter(ChatActivity.this, new ArrayList<ChatMessage>());
        messagesContainer.setAdapter(adapter);

                for(int i=0; i<chatHistory.size(); i++) {
                    ChatMessage message = chatHistory.get(i);
                    displayMessage(message);
                }

    }
    private void scroll() {
        messagesContainer.setSelection(messagesContainer.getCount() - 1);
    }

 }