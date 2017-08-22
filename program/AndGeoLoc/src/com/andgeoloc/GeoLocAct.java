package com.andgeoloc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class GeoLocAct extends Activity {

	TextView status;
	Button gpsbtn;
	LocationManager lm;
	LocationListener ll;
	String provider;
	String dis,name;
	String result="";
	ProgressDialog pg;
	double lat, lng;
	float distance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_loc);
        
        status=(TextView)findViewById(R.id.textView1);
        //gpsbtn=(Button)findViewById(R.id.button1);
        
        lm=(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        provider = lm.getBestProvider(criteria, false);
    	dis = status.getText().toString();
    	name = status.getText().toString();
		
       /// Location location = lm.getLastKnownLocation(provider);

      /*  if(status.equals("")||status.equals(""))
		{
			//output.setText("Please Enter User ID & Password");
			Toast.makeText(GeoLocAct.this,"Error", Toast.LENGTH_LONG).show();
			
		}
		else
		{
		background bg = new background();
		bg.execute();
	
		}*/
       
        ll=new LocationListener() {
			
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				 Toast.makeText(GeoLocAct.this,"GPS/Use Wireless network is not enabled",Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				
				  status.setText(String.valueOf(location.getLatitude())+","+String.valueOf(location.getLongitude()));
			         Toast.makeText(GeoLocAct.this,"New Location deteted",Toast.LENGTH_SHORT).show();
			         
			          lat = (int) (location.getLatitude());
			          lng = (int) (location.getLongitude());
			        // int time =(int)(location.getTime());
			         distance=(float)(location.distanceTo(location));
			        
			         
			         
			       /*  location.setLatitude(...);
			         location.setLongitude(...);
			         Location source= // ....
			         source.distanceTo(location);*/
			    
			         status.setText(String.valueOf(lat));
			         status.setText(String.valueOf(lng));
			         status.setText(String.valueOf(distance));
			         background bg = new background();
					 bg.execute();
			         
			  
					/* Intent anIntent=new Intent(this,SendSMSActivity.class);
					 PendingIntent operation=PendingIntent.getActivity(this,0,anIntent, Intent.FLAG_GRANT_READ_URI_PERMISSION); 
					 lm.addProximityAlert(location.getLatitude(), location.getLongitude(), 1000, -1, operation);*/

					 
			}
		};
		
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, ll);
	
    }

    public void postData() {
    	
    	InputStream isr = null;
		
		
        
		try{   
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();

        HttpPost httppost = new HttpPost("http://10.0.2.2/update_status.php");
     //   HttpPost httppost = new HttpPost("http://192.168.1.100/update_status.php");
        ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        postParameters.add(new BasicNameValuePair("uname", "AMIT"));
        postParameters.add(new BasicNameValuePair("udistance", ""+distance));

        httppost.setEntity(new UrlEncodedFormEntity(postParameters));
		
        HttpResponse response=httpclient.execute(httppost);
        
        
        response=httpclient.execute(httppost);
        	        
        String res=response.toString();
        System.out.println(res);
        
        res= res.replaceAll("\\s+","");
                
        if(res!=null)
        {
        	HttpEntity entity = response.getEntity();
        	isr = entity.getContent();
        	
        	InputStreamReader isre= new InputStreamReader(isr,"iso-8859-1");
            BufferedReader reader = new BufferedReader(isre,8);
            StringBuilder sb = new StringBuilder();
            String line = null; 
            while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
            }
       
            isr.close();
            result=sb.toString();   
            System.out.println(result);
        	// cut form here

        	
	    }

        } catch (ClientProtocolException e) {
            //Toast.makeText(this, "Error", 5000).show();
        }
        catch (IOException e) {
            //Toast.makeText(this, "Error", 5000).show();
        }
    } 
    
    
    
    
    class background extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			postData();
			return null;
		}

		@Override
		protected void onPostExecute(Void result1) {
			// TODO Auto-generated method stub
			super.onPostExecute(result1);
			
			Toast.makeText(GeoLocAct.this, result, Toast.LENGTH_LONG).show();
			if(result.startsWith("Out"))
        	{
				//output.setText(result);
        		//Intent i = new Intent();
	              //	i.putExtra("userid", id);
	              	//i.setClass(LoginActivity.this, WelcomeActivity.class);
	              	//startActivity(i);
	              	finish();
	              	pg.dismiss();
        	}
        	
        	if(result.charAt(0)=='D')
        	{
        		
               // output.setText("Incorrect Userid or Password ");
            	Toast.makeText(GeoLocAct.this, result, Toast.LENGTH_LONG).show();
            }
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			//progress();
		}

		
    	
    	
    	
    }
    public void progress()
	{
		pg = new ProgressDialog(GeoLocAct.this);
		pg.setTitle("");
		pg.setMessage("Connecting Please Wait.........");
		pg.setCancelable(true);
        pg.setIndeterminate(true);
		pg.show();
	}
    
   
   /* private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
         return (dist);
      }

     private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
      }
     private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
      }*/
}
