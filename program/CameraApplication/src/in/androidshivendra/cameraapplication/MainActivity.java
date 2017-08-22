package in.androidshivendra.cameraapplication;

import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.Window;

import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnClickListener {
	
	ImageView iv;
	ImageButton ib;
	Button btn;
	Intent i;
	final static int cameraData = 0;
	Bitmap bmp;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		initialize();
		InputStream is = getResources().openRawResource(R.drawable.logo);
		bmp = BitmapFactory.decodeStream(is);

				
	}
	

	private void initialize() {
		// TODO Auto-generated method stub
		iv = (ImageView)findViewById(R.id.imageView1);
		ib = (ImageButton)findViewById(R.id.imageButton1);
		btn = (Button)findViewById(R.id.button1);
		btn.setOnClickListener(this);
		ib.setOnClickListener(this);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.button1:
			
			try {
				getApplicationContext().setWallpaper(bmp);
				final AlertDialog.Builder alert = new AlertDialog.Builder(this);			 // WHY NOT WORKING? AND ALSO WHY APP NOT WORKING WHEN SCREEN ROTATION IS ON??
				alert.setTitle("Alert!!!!!!!");
				alert.setMessage("Wallpaper Successfully Changed!!!");
				alert.setCancelable(true);
				alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				}).show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	

			break;
		case R.id.imageButton1:
			i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); // action for opening camera
			startActivityForResult(i, cameraData);
		
			break;
			
		}
		
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode== RESULT_OK){
			
			Bundle extras = data.getExtras();
			bmp = (Bitmap) extras.get("data"); // data is a key 
			iv.setImageBitmap(bmp);
			
		}
	}

}
