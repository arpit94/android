package com.ajay.contactsbyvoice;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.PhoneLookup;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


 
public class MainActivity extends Activity {
 
    protected static final int RESULT_SPEECH = 1;
 
    ImageButton btnSpeak;
    TextView txtText,personname,allcontact;
    String str;
    Button getname;
    StringBuilder myNumbers;
    String contact;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        txtText = (TextView)findViewById(R.id.txtText);
        allcontact = (TextView)findViewById(R.id.textView1);
        personname = (TextView)findViewById(R.id.contpersonname);
        btnSpeak = (ImageButton)findViewById(R.id.btnSpeak);
        getname = (Button)findViewById(R.id.button1);
 
        btnSpeak.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View v) {
 
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH); 
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
                
                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                    txtText.setText("");
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(),"Opps! Your device doesn't support Speech to Text",Toast.LENGTH_SHORT).show();
                   }
            }
        });
        
        
        getname.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int num = Integer.parseInt(myNumbers.toString());
				
				
        	    Cursor people = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, Phone.DISPLAY_NAME + " ASC");
        	    while(people.moveToNext()) {
        	    	
        	    	int x = people.getPosition();
        	    	System.out.println(x);
        	    	
        	    	if(x==num){
        	       int nameFieldColumnIndex = people.getColumnIndex(PhoneLookup.DISPLAY_NAME);
        	       contact = people.getString(nameFieldColumnIndex); 
        	       personname.setText(contact);
        	       break;
        	    	}
        	    }
        	    people.close();
        	    

				
        	    
        	    
        	    Cursor peoplelist = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, Phone.DISPLAY_NAME + " ASC");
        	    StringBuilder sb = new StringBuilder();
        	    while(peoplelist.moveToNext()) {
        	    
        	    	
        	       int nameFieldColumnIndex1 = peoplelist.getColumnIndex(PhoneLookup.DISPLAY_NAME);
        	       contact = peoplelist.getString(nameFieldColumnIndex1);         	       
        	       sb.append( "\nName : "+contact);
        	    }
        	    peoplelist.close();
        	    
        	    allcontact.setText(sb.toString());

        	    
        	    
				
			}
		});
 
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
 
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
 
        switch (requestCode) {
        case RESULT_SPEECH: {
            if (resultCode == RESULT_OK && null != data) {
 
                ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
 
           
                str = text.get(0);
        	    myNumbers = new StringBuilder();
        	    for (int i = 0; i < str.length(); i++) {
        	        if (Character.isDigit(str.charAt(i))) {
        	            myNumbers.append(str.charAt(i));
        	            System.out.print(str.charAt(i) + " is a digit.");
        	        } else {
        	            System.out.println(str.charAt(i) + " not a digit.");
        	        }
        	    }
        	     txtText.setText(str);
        	    System.out.println("Your numbers: " + myNumbers.toString());
        	            	 
            }
            break;
        }
 
        }
    }
    
    
    
    
}