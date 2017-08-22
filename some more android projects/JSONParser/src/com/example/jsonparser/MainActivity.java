package com.example.jsonparser;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {
Button btn ;
TextView tv;
String result,record1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.button1);
        tv =(TextView)findViewById(R.id.textView1);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				new ParseJSon().execute();
			}
		});
        
    }
    class ParseJSon extends AsyncTask<Void,Void, Void>
    {
    	ProgressDialog pd;
@Override
protected void onPreExecute() {
	// TODO Auto-generated method stub
	super.onPreExecute();
	pd = new ProgressDialog(MainActivity.this);
	pd.setMessage("Fetching Information ");
	pd.show();
}
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			getData();
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd.dismiss();
			tv.setText(record1);
		}
    	
    }
    public void getData()
    {
     
     InputStream isr = null;
     // To getting Data
     try
     {
        HttpClient httpclient = new DefaultHttpClient();
             HttpPost httppost = new HttpPost("http://apsmind.com/foodcorner/fetchfeedback.php");
                  
             
              HttpResponse response = httpclient.execute(httppost);
               if(response!=null)
                System.out.println("Connection created");
              HttpEntity entity = response.getEntity();
               isr = entity.getContent();
             

     }
     catch(Exception e)
     {
      Log.i("Error",e.toString());
   
     }
       //convert response to string
     try{
      InputStreamReader isre= new InputStreamReader(isr,"iso-8859-1");
             BufferedReader reader = new BufferedReader(isre,8);
             StringBuilder sb = new StringBuilder();
             String line = null;
             while ((line = reader.readLine()) != null) {
            	 if(line.startsWith("<"))
            		 continue;
                     sb.append(line + "\n");
             }
             isr.close();
             result=sb.toString();
             System.out.println(sb);
           //  tv.setText(result);
     }catch(Exception e){
             Log.e("log_tag", "Error converting result "+e.toString());
     }
  try{
    
        JSONArray    jArray = new JSONArray(result);
        for(int i=0;i<jArray.length();i++)
        {
         JSONObject json = jArray.getJSONObject(i);
         record1 = record1+"\n" +i+" Name : "+json.getString("name")+
           " Rating: "+json.getString("Rating")+" Comment: "+json.getString("comment");
        
        }
        System.out.println(record1);
     }catch(JSONException e){
             Log.e("log_tag", "Error parsing data "+e.toString());
     }
    
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
