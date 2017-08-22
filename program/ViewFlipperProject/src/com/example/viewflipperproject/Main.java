package com.example.viewflipperproject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher.ViewFactory;

public class Main extends Activity {
	ViewFlipper vf ;
	 float lastX;
	 

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
         vf = (ViewFlipper)findViewById(R.id.view_flipper);
         
         
         
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onTouchEvent(MotionEvent touchevent) {
    switch (touchevent.getAction())
    {
    case MotionEvent.ACTION_DOWN:
    {
    lastX = touchevent.getX();
    break;
    }
    case MotionEvent.ACTION_UP:
    {
    float currentX = touchevent.getX();
    Toast.makeText(this, ""+currentX, Toast.LENGTH_LONG).show();
    if (lastX < currentX)
    {
    if ( vf.getDisplayedChild()==0) break;
    vf.setInAnimation(this, R.anim.in_from_left);
    vf.setOutAnimation(this, R.anim.out_to_right);
    vf.showNext();
    }
    if (lastX > currentX)
    {
    if (vf.getDisplayedChild()==1) break;
    vf.setInAnimation(this, R.anim.in_from_right);
    vf.setOutAnimation(this, R.anim.out_to_left);
    vf.showPrevious();
    }
    break;
    }
    }
    return false;
    }
}
