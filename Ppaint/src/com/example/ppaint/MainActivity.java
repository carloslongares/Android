package com.example.ppaint;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MiVista(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	
	
	
	public class MiVista extends View {
		public MiVista (Context contexto){
			super(contexto);
		}
		
		
		
		float x ;
	    float y ;
	    Paint pincel = new Paint();
		Path path = new Path();
		
		protected void onDraw (Canvas canvas){
			pincel.setColor(Color.BLACK);
			pincel.setStrokeWidth(15);
			pincel.setStyle(Style.STROKE);			
			canvas.drawPath(path, pincel);
		}

		 public boolean onTouchEvent(MotionEvent event) {
		 
			 x = event.getX();
	         y = event.getY();
	        
		        if ( event.getAction() == MotionEvent.ACTION_DOWN){
		        		path.moveTo(x, y);
		        	}
		        
		        if (event.getAction() == MotionEvent.ACTION_MOVE)
		        	{
		        		path.lineTo(x, y);
		        	}
		   
		        invalidate();
		        return true;
		        };

	}
}
