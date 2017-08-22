package com.example.videoplayer;



import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {
VideoView vv ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		vv = (VideoView)findViewById(R.id.videoView1);
		MediaController mc = new MediaController(this);
		mc.setAnchorView(vv);
		vv.setMediaController(mc);
	String path = "android.resource://com.example.videoplayer/" + R.raw.main;
		vv.setVideoPath(path);
		vv.requestFocus();
		vv.start();
		
		vv.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
           	 Intent i = new Intent();
           	 i.setClass(getApplicationContext(), MainActivity.class);
           	 startActivity(i);
               
            }
        });
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
