package in.androidshivendra.androidanimationexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Test extends Activity implements AnimationListener {

	ImageView imgLogo;
	Button btnStart;
	int i=0;

	// Animation
	Animation animSequential;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sec);
		LinearLayout l1  = (LinearLayout)findViewById(R.id.l1);
		imgLogo = (ImageView) findViewById(R.id.iv_up_arrow);
		//btnStart = (Button) findViewById(R.id.btnStart);

		// load the animation
		animSequential = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.move);

		// set animation listener
		animSequential.setAnimationListener(this);
		
		l1.startAnimation(animSequential);

		/*// button click event
		btnStart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// start the animation
				imgLogo.startAnimation(animSequential);
			}
		});*/
		
		l1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(Test.this, "hello"+(++i), Toast.LENGTH_LONG).show();
			}
		});

	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// Take any action after completing the animation

		// check for zoom in animation
		if (animation == animSequential) {
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

}