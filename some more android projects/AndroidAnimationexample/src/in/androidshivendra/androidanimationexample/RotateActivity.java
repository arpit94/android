package in.androidshivendra.androidanimationexample;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class RotateActivity extends Activity implements AnimationListener {

	ImageView imgLogo;
	Button btnStart;

	// Animation
	Animation animRotate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rotate);

		imgLogo = (ImageView) findViewById(R.id.imgLogo);
		btnStart = (Button) findViewById(R.id.btnStart);

		// load the animation
		animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.rotate);

		// set animation listener
		animRotate.setAnimationListener(this);

		// button click event
		btnStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				imgLogo.setVisibility(View.VISIBLE);

				// start the animation
				imgLogo.startAnimation(animRotate);
			}
		});

	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// Take any action after completing the animation

		// check for fade in animation
		if (animation == animRotate) {
			newanim();
		}

	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}
	
	@SuppressLint("NewApi")
	public void newanim()
	{
		try{
		ObjectAnimator anim = (ObjectAnimator) 
				AnimatorInflater.loadAnimator(getApplicationContext(), 
						R.anim.rotation3d); 
		anim.setTarget(imgLogo);
		anim.setDuration(3000);
		anim.start();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}