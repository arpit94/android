package in.androidShivendra.gestureproject;

//MAIL THIS TO 
//aaryamannsingh2@gmail.com
import java.util.ArrayList;


import android.os.Bundle;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;


import android.view.Menu;
import android.view.View;

import android.widget.Toast;

public class GestureProject extends Activity implements OnGesturePerformedListener {
	private GestureLibrary gestureLib;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_gesture_project);
		GestureOverlayView gestureOverlayView = new GestureOverlayView(this);
	    View inflate = getLayoutInflater().inflate(R.layout.activity_gesture_project, null);
	    gestureOverlayView.addView(inflate);
	    
	  //MAIL THIS TO 
	  //aaryamannsingh2@gmail.com
	    
	    gestureOverlayView.addOnGesturePerformedListener(this);
	    gestureLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
	    if (!gestureLib.load()) {
	    	Toast.makeText(GestureProject.this, "Fail to load lib ", Toast.LENGTH_LONG).show();
	      finish();
	    }
	  //MAIL THIS TO 
	  //aaryamannsingh2@gmail.com
	    setContentView(gestureOverlayView);
	  }

	//MAIL THIS TO 
	//aaryamannsingh2@gmail.com	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gesture_project, menu);
		return true;
	}

	@Override
	public void onGesturePerformed(GestureOverlayView arg0, Gesture gesture) {
		// TODO Auto-generated method stub
		 ArrayList<Prediction> predictions = gestureLib.recognize(gesture);
		    for (Prediction pre : predictions){
		      if (pre.score > 2.0) {
		        Toast.makeText(this, pre.name, Toast.LENGTH_SHORT).show();
		      }
		    }
		  }
		

}
//MAIL THIS TO 
//aaryamannsingh2@gmail.com