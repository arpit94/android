package com.example.galleryproject;



import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ViewSwitcher.ViewFactory;

import android.widget.ImageView;
import android.widget.Toast;

public class Galleryimg extends Activity implements ViewFactory {
int imgid[]={R.drawable.pic1,R.drawable.pic2,R.drawable.pic3};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_gallery);
       Button btn=(Button)findViewById(R.id.click);
       btn.setOnClickListener(new OnClickListener()
       {
		@Override
		public void onClick(View v) 
		{
			System.out.println("Hello");
			Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_LONG).show();
			
			
		}
	});
       
        Gallery g = (Gallery)findViewById(R.id.gallery1);
        g.setAdapter(new imgadapter(this));
        final ImageSwitcher isa= (ImageSwitcher) findViewById(R.id.imageSwitcher1);
        isa.setFactory(this);
        g.setAdapter(new imgadapter(this));
        g.setOnItemClickListener(new OnItemClickListener()
        {
        	
            public void onItemClick(AdapterView<?> parent,
            View v, int position, long id)
            {
                isa.setImageResource(imgid[position]);
                
            }
        });
        
     
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_gallery, menu);
        return true;
    }
    public class imgadapter extends BaseAdapter
    {
    	Context c;
    	

    	public imgadapter(Context m)
    	{
    		c = m;
    		TypedArray a = obtainStyledAttributes(R.styleable.Gallery1);
           
            a.recycle();
    	}
    	
		@Override
		public int getCount()
		{
			// TODO Auto-generated method stub
			return imgid.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
        {
            ImageView imageView = new ImageView(c);
            
            imageView.setImageResource(imgid[position]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new Gallery.LayoutParams(150, 120));
            
            
            return imageView;
        }
    }
    @Override
	public View makeView() {
		// TODO Auto-generated method stub
		ImageView imageView = new ImageView(this);
        
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setLayoutParams(new
                ImageSwitcher.LayoutParams(
                        LayoutParams.FILL_PARENT,
                        LayoutParams.FILL_PARENT));
        return imageView;
	}
 

}
