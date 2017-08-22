/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.android.media;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoViewDemo extends Activity {
	private static final String TAG = "VideoViewDemo";

	private VideoView mVideoView;
	private EditText mPath;
	private ImageButton mPlay;
	private ImageButton mPause;
	private ImageButton mReset;
	private ImageButton mStop;
	private String current;

	public int count=0;
	public String[] str;
	 
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		
		mVideoView = (VideoView) findViewById(R.id.surface_view);
		mPath = (EditText) findViewById(R.id.path);
		File f=new File("/mnt/sdcard");
		str=f.list();
		mPath.setText("/mnt/sdcard/"+str[count++]); 
		for(int i=0;i<str.length;i++)
		 {
			 Log.i("Song name",str[i]);
		 }
		/*mPlay = (ImageButton) findViewById(R.id.play);
		mPause = (ImageButton) findViewById(R.id.pause);
		mReset = (ImageButton) findViewById(R.id.reset);
		mStop = (ImageButton) findViewById(R.id.stop);
		
		mPlay.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				
				if(count==6)
					count=0;
				mPath.setText("/mnt/sdcard/"+str[count++]);
				playVideo();
			}
		});
		mPause.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				if (mVideoView != null) {
					((VideoView) mVideoView).pause();
				}
			}
		});
		mReset.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				if (mVideoView != null) {
					//mVideoView.seekTo(0);
					if(count==0)
						mPath.setText("/mnt/sdcard/"+str[count]);
					else
						mPath.setText("/mnt/sdcard/"+str[--count]);
					playVideo();
				}
			}
		});
		mStop.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				if (mVideoView != null) {
					current = null;
					((VideoView) mVideoView).stopPlayback();
				}
			}
		});
		runOnUiThread(new Runnable(){
			public void run() {
				playVideo();
			}
			
		});*/
	}

	private void playVideo() {
		try {
			
			final String path = mPath.getText().toString();
			Log.v(TAG, "path: " + path);
			if (path == null || path.length() == 0) {
				Toast.makeText(VideoViewDemo.this, "File URL/path is empty",
						Toast.LENGTH_LONG).show();
			} else {
				// If the path has not changed, just start the media player
				if (path.equals(current) && mVideoView != null) {

					((VideoView) mVideoView).start();
					mVideoView.requestFocus();
					return;
				}
				current = path;
				((VideoView) mVideoView).setVideoPath(getDataSource(path));
				((VideoView) mVideoView).start();
				mVideoView.requestFocus();
			}
		} catch (Exception e) {
			Log.e(TAG, "error: " + e.getMessage(), e);
			if (mVideoView != null) {

				((VideoView) mVideoView).stopPlayback();
			}
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
		
		if(keyCode==KeyEvent.KEYCODE_DPAD_LEFT)
		{
			if (mVideoView != null) {
				//mVideoView.seekTo(0);
				if(count==0)
					mPath.setText("/mnt/sdcard/"+str[count]);
				else
					mPath.setText("/mnt/sdcard/"+str[count--]);
				playVideo();
			}
		}
		if(keyCode==KeyEvent.KEYCODE_DPAD_RIGHT)
		{
			if(count==10)
				count=0;
			mPath.setText("/mnt/sdcard/"+str[count++]);
				playVideo();
		}
		if(keyCode==KeyEvent.KEYCODE_DPAD_UP)
		{
			if (mVideoView != null) {
				//mVideoView.seekTo(0);
				count=0;
				mPath.setText("/mnt/sdcard/"+str[count++]);
				playVideo();
			}
		}
		if(keyCode==KeyEvent.KEYCODE_DPAD_DOWN)
		{
			if (mVideoView != null) {
				//mVideoView.seekTo(0);
				mPath.setText("/mnt/sdcard/"+str[str.length-1]);
				playVideo();
			}
		}
		if(keyCode==KeyEvent.KEYCODE_DPAD_CENTER)
		{
			if(
					((VideoView) mVideoView).isPlaying())
			{

				((VideoView) mVideoView).pause();
			}
			else
			{

				((VideoView) mVideoView).resume();
				playVideo();
			}
		}
		return super.onKeyDown(keyCode, event);
		
	}
	

	private String getDataSource(String path) throws IOException {
		if (!URLUtil.isNetworkUrl(path)) {
			return path;
		} else {
			URL url = new URL(path);
			URLConnection cn = url.openConnection();
			cn.connect();
			InputStream stream = cn.getInputStream();
			if (stream == null)
				throw new RuntimeException("stream is null");
			File temp = File.createTempFile("mediaplayertmp", "dat");
			temp.deleteOnExit();
			String tempPath = temp.getAbsolutePath();
			FileOutputStream out = new FileOutputStream(temp);
			byte buf[] = new byte[128];
			do {
				int numread = stream.read(buf);
				if (numread <= 0)
					break;
				out.write(buf, 0, numread);
			} while (true);
			try {
				stream.close();
			} catch (IOException ex) {
				Log.e(TAG, "error: " + ex.getMessage(), ex);
			}
			
			return tempPath;
		}
	}
	
}
