package com.androidhive.pushnotifications;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Chatlist extends Activity {
String result="";
String result1="";
Record record;
Record eventslist;
ListView lv;
Button btn;
CustomAdapter cadp;

SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chatlist);
		record=new Record(Chatlist.this);
		
		eventslist=new Record(this);
		db=eventslist.getReadableDatabase();
		btn=(Button)findViewById(R.id.button1);
		lv=(ListView)findViewById(R.id.listView1);
		readinternaldata();
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new updatefrndlist().execute();
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chatlist, menu);
		return true;
	}
class updatefrndlist extends AsyncTask<Void,Void,Void>
{
	ProgressDialog pd;
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pd=new ProgressDialog(Chatlist.this);
		pd.setMessage("Refreshing..");
		pd.show();
		db=record.getWritableDatabase();/*to open database in writable format*/
		
	}
	@Override
	protected Void doInBackground(Void... arg0) {
		// TODO Auto-generated method stub
		getData();
		return null;
		
	}
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		pd.dismiss();
		 //lv.setAdapter(adp);
		 int pos=lv.getSelectedItemPosition();
		 System.out.println(pos);		 
		 readinternaldata();
			}
	
	
}
public void getData()
{	InputStream isr=null;
	String key;
	String name;
	
	try {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://androidtraning.net63.net/gcm_server_php/getuserinfo.php");
        HttpResponse response = httpClient.execute(httpPost);
        if (response != null)
            System.out.println("Connection created");
        HttpEntity entity = response.getEntity();
        isr = entity.getContent();
    }
    catch (Exception e) {
        Log.i("Error", e.toString());
    }
	try{
        InputStreamReader isre=new InputStreamReader(isr,"iso-8859-1");
        BufferedReader reader=new BufferedReader(isre,8);
        StringBuilder sb=new StringBuilder();
        String line=null;
        while((line=reader.readLine())!=null){
            sb.append(line+"\n");

        }
        result=sb.toString();
        System.out.println(sb);

    }
    catch (Exception e)
    {
        Log.e("log_tag", "Error Converting result" + e.toString());
    }
	try {
        JSONArray jsonArray = new JSONArray(result);
        result1="";
        for(int i=0;i<jsonArray.length();i++)
        {
            JSONObject json=jsonArray.getJSONObject(i);
           key=json.getString("gcm_regid");
           name=json.getString("name");
           System.out.println(key+name);
           ContentValues values= new ContentValues();/*a class whose object helps to insert values in table*/
			values.put(Record.Col1_gcmid, key);
			values.put(Record.Col2_name, name);
			db.insert(Record.Table_name, null, values);//null stands for null hacker(insert null for not defined positions
			//adp.add(name);
        }

    }catch(Exception e)
    {
        Log.e("log_tag", "Error parsing data " + e.toString());

    }

   
}
void readinternaldata(){
	cadp=new CustomAdapter(this, 0,generatedata());
	
	lv.setAdapter(cadp);
	lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Contact person=(Contact) lv.getItemAtPosition(arg2);
		Intent i=new Intent(Chatlist.this,ChatActivity.class);
		i.putExtra("name",person.get_name());
		i.putExtra("gcm_id", person.get_key());
		startActivity(i);
			
		}
	});
}
ArrayList<Contact> generatedata()
{
	ArrayList<Contact> items =new ArrayList<Contact>();
	
	Cursor cursor=db.query(Record.Table_name, null, null, null, null, null, null);
	while(cursor.moveToNext())
	{
		String name=cursor.getString(2);
		String gcm_id=cursor.getString(1);
		items.add(new Contact(cursor.getLong(0),name,gcm_id));
		
	}
return items;
}
}
