package com.apsmind.ddbconnect;

import android.os.Bundle;
import android.provider.BaseColumns;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.InputFilter.LengthFilter;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText n1,phno,e1,add,edit;
	Button b1,b2,b3,b4,b5;

	
Record record;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		n1=(EditText)findViewById(R.id.editText1);
		phno=(EditText)findViewById(R.id.editText2);
		e1=(EditText)findViewById(R.id.editText3);
		add=(EditText)findViewById(R.id.editText4);
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		b3=(Button)findViewById(R.id.button3);
		b4=(Button)findViewById(R.id.button4);
		b5=(Button)findViewById(R.id.button5);
		edit=(EditText)findViewById(R.id.editText5);
		
		record=new Record(MainActivity.this);
		
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name=n1.getText().toString();
				String phone=phno.getText().toString();
				String email=e1.getText().toString();
				String address=add.getText().toString();
				
				
				SQLiteDatabase db=record.getWritableDatabase();/*to open database in writable format*/
				ContentValues values= new ContentValues();/*a class whoese object helps to insert values in table*/
				values.put(Record.Col1_name, name);
				values.put(Record.Col3_password, phone);
				values.put(Record.Col2_email,email );
				values.put(Record.Col4_address, address);
				db.insert(Record.Table_name, null, values);//null stands for null hacker(insert null for not defined positions
				n1.setText("");
				phno.setText("");
				e1.setText("");
				add.setText("");
				Toast.makeText(MainActivity.this, "Data inserted successfull ", Toast.LENGTH_LONG).show();
			}
		});
		b2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(MainActivity.this,Activity1.class);
				startActivity(i);
			}
		});
		//delete by id
		b3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String id= edit.getText().toString();
			SQLiteDatabase db=record.getWritableDatabase();
			try{
			db.delete(Record.Table_name, BaseColumns._ID+"="+id, null);
			}
			catch(Exception e)
			{
				Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
			}
			Toast.makeText(MainActivity.this,"deleted", Toast.LENGTH_SHORT).show();
			
				
			}
		});
		//update value by id
		b5.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String id= edit.getText().toString();
				
				String name=n1.getText().toString();
				String phone=phno.getText().toString();
				String email=e1.getText().toString();
				String address=add.getText().toString();
				try{
				SQLiteDatabase db=record.getWritableDatabase();/*to open database in writable format*/
				ContentValues values= new ContentValues();/*a class whoese object helps to insert values in table*/
				values.put(Record.Col1_name, name);
				values.put(Record.Col3_password, phone);
				values.put(Record.Col2_email,email );
				values.put(Record.Col4_address, address);
				db.update(Record.Table_name, values, BaseColumns._ID+"="+id, null);
				n1.setText("");
				phno.setText("");
				e1.setText("");
				add.setText("");
				}
				catch(Exception e)
				{
					Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
				}
				
				
			}
		});
		//get value by id
		b4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String id= edit.getText().toString();
				try{
				SQLiteDatabase db=record.getReadableDatabase();
				Cursor mCursor =
		                db.query( Record.Table_name,null, BaseColumns._ID+"="+id, null,
		                null, null, null, null);
		        if (mCursor != null) {
		           while(mCursor.moveToNext())
		           {
		            n1.setText(mCursor.getString(1));
		            phno.setText(mCursor.getString(2));
		            e1.setText(mCursor.getString(3));
		            add.setText(mCursor.getString(4));
		           }
		        }
				}
				catch(Exception e)
				{
					Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();	
				}
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
