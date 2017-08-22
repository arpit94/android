package com.apsmind.proximitsensorexample;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener{
TextView tv ;
SensorManager smgr;
Sensor msensor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView)findViewById(R.id.textView1);
		smgr = (SensorManager)getSystemService(SENSOR_SERVICE);
	//msensor = smgr.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		msensor = smgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	//msensor = smgr.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
		protected void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			smgr.registerListener(MainActivity.this, msensor, SensorManager.SENSOR_DELAY_NORMAL);
		}
	@Override
		protected void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
			smgr.unregisterListener(this);
		}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
	/*	if(event.values[0]>0)
		{
			tv.setText("Object far");
			
		}
		else
		{
			tv.setText("Object Nearer");
		}
	*/	
		float x = event.values[0];
		float y = event.values[1];
		float z = event.values[2];
		
		float avg = (x+y+z)/3;
		tv.setText("X : "+x+" Y: "+y+ " Z :"+z);
		if(avg>=5.0)
		{
			tv.setBackgroundColor(Color.GREEN);
			
			
			
		}
		else
		{
			tv.setBackgroundColor(Color.RED);
		}
	}

}
