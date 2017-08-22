package in.androidshivendra.androidwebview;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class AndroidWeb extends Activity {

	
	WebView wv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_web);
		wv = (WebView)findViewById(R.id.webView1);
		new Prog().execute();
		wv.setWebViewClient(new Callback());
        WebSettings webSettings = wv.getSettings();
        webSettings.setBuiltInZoomControls(true);

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.android_web, menu);
		return true;
	}
	class  Prog extends AsyncTask<Void, Void, Void>
	{
ProgressDialog  pd ;
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
		pd.dismiss();
		Toast.makeText(AndroidWeb.this, "Test", Toast.LENGTH_LONG).show();
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			pd = new ProgressDialog(AndroidWeb.this);
			pd.setTitle("Website Loaded");
			pd.setMessage("keep patitence");
		
			pd.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			wv.loadUrl("http://10.0.2.2/androidtrainer.netne.net");
			
		
			return null;
		}
		
	}
	
	  private class Callback extends WebViewClient {
	        @Override
	        public boolean shouldOverrideUrlLoading(WebView view, String url) {
	            return(false);
	        }
	        }
	  

}
