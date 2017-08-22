package in.androidshivendra.androidform;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button btn;
	EditText ename, epass;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn = (Button)findViewById(R.id.button1);
		
		ename = (EditText)findViewById(R.id.editText1);
		epass  = (EditText)findViewById(R.id.editText2);
		
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("Hello Friend");
				
				
				String name = ename.getText().toString();
				String pass = epass.getText().toString();
				Toast.makeText(MainActivity.this, "Name  "+name+" password "+pass, Toast.LENGTH_LONG).show();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);;
		return true;
	}

}
