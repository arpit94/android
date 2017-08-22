package com.example2.arpitaggarwal.sensors;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.List;


public class MainActivity extends Activity  implements SensorEventListener{

    SensorManager sensorManager;
    List<Sensor> sensorList;
    ListView lv;
    Button btn;
    ArrayAdapter<String> adp;
    RelativeLayout rlay;
    Sensor sens;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager =(SensorManager)getSystemService(SENSOR_SERVICE);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        sens =sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        rlay = (RelativeLayout)findViewById(R.id.back);
        adp =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        for(Sensor s: sensorList)
        {
            adp.add(s.getName());
        }
        lv =(ListView)findViewById(R.id.listView);
        btn=(Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv.setAdapter(adp);
            }
        });
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

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        if(x>0)
            rlay.setBackgroundColor(Color.RED);
        else
            rlay.setBackgroundColor(Color.GREEN);
        btn.setText(""+x);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sens,sensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
