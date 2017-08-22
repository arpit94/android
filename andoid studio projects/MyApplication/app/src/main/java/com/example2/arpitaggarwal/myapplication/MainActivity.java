package com.example2.arpitaggarwal.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends Activity {
    EditText edt1, edt2;
    Button submit;
    String result,record1;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1 = (EditText) findViewById(R.id.editText);
        edt2 = (EditText) findViewById(R.id.editText2);
        submit = (Button) findViewById(R.id.submit);
        tv=(TextView)findViewById(R.id.textView);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ParseJson().execute();
            }
        });
    }

    class ParseJson extends AsyncTask<Void, Void, Void> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Fetching Information");
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            getData();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pd.dismiss();

        }
    }

    public void getData(){
        InputStream isr=null;
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://10.0.2.2/fetchdb1.php");
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
            Log.e("log_tag","Error Converting result" +e.toString());
        }
        try {
            JSONArray jsonArray = new JSONArray(result);
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject json=jsonArray.getJSONObject(i);
                record1=record1+"\n"+i+"Name: "+json.getString("Name")+" Price :"+json.getString("Price");

            }
            tv.setText(record1.toString());
        }catch(Exception e)
        {
            Log.e("log_tag", "Error parsing data " + e.toString());

        }

        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
