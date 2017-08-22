package com.example.alarmservicep;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		new Mysyn().execute();
/*new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
					Thread.sleep(800);
					
				}
				catch(Exception e)
				{}
			}
		}).start();*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
	class Mysyn extends AsyncTask<Integer, Integer, Void>
	{
		
		ProgressDialog pd  ;
		 @Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
		
			super.onPreExecute();
			
			pd = new ProgressDialog(Splash.this);
			pd.setMessage("processing...");
			pd.setTitle("Download Start ");
			pd.setIcon(R.drawable.ic_launcher);
			pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			pd.show();
			
		}

		@Override
		protected Void doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			
			for(int i = 0 ;i<100;i++)
			{
				try {
					Thread.sleep(100);
					Log.d("count",""+i);
					publishProgress(i);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			return null;
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		//	tv.setText(""+values[0]);
			pd.setProgress(values[0]);
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			pd.cancel();
			Intent i = new Intent(Splash.this, MainActivity.class);
			startActivity(i);
		}
		
	}

}
