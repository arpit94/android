package com.example.phonecallexample;






import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
       final EditText ephone = (EditText)findViewById(R.id.editText1);
        Button btn = (Button)findViewById(R.id.button1);
        
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String number=ephone.getText().toString();
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:"+number));//tell:8750475003
				startActivity(callIntent);
				
			}
		});
        
        final EditText emsg  = (EditText)findViewById(R.id.editText2);
        Button bmsg = (Button)findViewById(R.id.button2);
        
        
        bmsg.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String number=ephone.getText().toString();
				String msg = emsg.getText().toString();
				
				
				//Getting intent and PendingIntent instance
				Intent intent=new Intent(getApplicationContext(),MainActivity.class);
				PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
				
				//Get the SmsManager instance and call the sendTextMessage method to send message
				SmsManager sms=SmsManager.getDefault();
				sms.sendTextMessage(number, null, msg, pi,null);
				
				Toast.makeText(getApplicationContext(), "Message Sent successfully!",
					Toast.LENGTH_LONG).show();
	
				
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
