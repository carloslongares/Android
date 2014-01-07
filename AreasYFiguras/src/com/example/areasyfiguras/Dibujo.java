package com.example.areasyfiguras;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class Dibujo extends Activity{
	
	//Display display;
	public int x ;
	public int y ;
	

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		y = displaymetrics.heightPixels;
		x = displaymetrics.widthPixels;
		
		
		setContentView(new Vista(this));
	}
	
	

	
	public class Vista extends View {
		public Vista (Context contexto){
			super(contexto);
		}
		//this.setAnimation(null);
		int coorX;
		int coorY;
		int anchoPantalla = x;
		int altoPantalla= y;
		Paint pincel = new Paint();
		Paint lapiz= new Paint();
		Bundle bundle = getIntent().getExtras();

		String figura= bundle.getString("FIGURA");
		
		int color = bundle.getInt("COLOR");
		
		public boolean onTouchEvent(MotionEvent event) {
		    coorX = (int)event.getX();
		    coorY = (int)event.getY();
		    switch (event.getAction()) {
		        case MotionEvent.ACTION_DOWN:
		        	coorX = (int)event.getX();
		 		    coorY = (int)event.getY();
		        case MotionEvent.ACTION_MOVE:
		        	coorX = (int)event.getX();
		 		    coorY = (int)event.getY();
		        case MotionEvent.ACTION_UP:
		        	coorX = (int)event.getX();
		 		    coorY = (int)event.getY();
		    }
		return false;
		}
		
		protected void onDraw(Canvas canvas){
			this.setAnimation(null);
			lapiz.setColor(Color.BLACK);
			lapiz.setStrokeWidth(1);
			lapiz.setStyle(Style.FILL);
			canvas.drawPoint(coorX, coorY, pincel);
			
			if (figura.equalsIgnoreCase("circulo")){
				int coorX = anchoPantalla/2;
				int coorY = altoPantalla/2;
				int radio = Integer.parseInt(bundle.getString("RADIO"));
				String area= bundle.getString("AREA"); 
			
				
				pincel.setColor(color);
				pincel.setStrokeWidth(15);
				pincel.setStyle(Style.STROKE);
				if (radio>coorX){
				canvas.drawCircle(coorX,coorY,coorX,pincel);
				Intent intent = new Intent(Dibujo.this,MensajeAcercaDe.class);
				startActivity(intent);
				}
				canvas.drawCircle(coorX,coorY,radio,pincel);
				canvas.drawText(area, 600, 100, lapiz);
			}
			
			if (figura.equalsIgnoreCase("cuadrado"))
				{
				int lado = Integer.parseInt(bundle.getString("LADO"));
				if (lado>anchoPantalla)
					{
					lado=anchoPantalla;
					Intent intent = new Intent(Dibujo.this,MensajeAcercaDe.class);
					startActivity(intent);
					}
				
				pincel.setColor(color);
				pincel.setStrokeWidth(5);
				pincel.setStyle(Style.STROKE);
				
				canvas.drawRect(30, 30,30+lado,30+lado, pincel);
				}
			
			if (figura.equalsIgnoreCase("rectangulo")){
				int ancho = Integer.parseInt(bundle.getString("ANCHO"));
				int alto = Integer.parseInt(bundle.getString("ALTO"));
				
				pincel.setColor(color);
				pincel.setStrokeWidth(15);
				pincel.setStyle(Style.STROKE);
				
				if (alto > altoPantalla && ancho > anchoPantalla)
					{
					canvas.drawRect(30, 30, anchoPantalla, altoPantalla-300, pincel);
					Intent intent = new Intent(Dibujo.this,MensajeAcercaDe.class);
					startActivity(intent);	
					}
					else if (alto>altoPantalla)
						{
						canvas.drawRect(30, 30, ancho+30, altoPantalla-300, pincel);
						Intent intent = new Intent(Dibujo.this,MensajeAcercaDe.class);
						startActivity(intent);
						}
						else if (ancho > anchoPantalla)
							{
							canvas.drawRect(30, 30, anchoPantalla, alto+30, pincel);
							Intent intent = new Intent(Dibujo.this,MensajeAcercaDe.class);
							startActivity(intent);
							}
							else
								{
								canvas.drawRect(30, 30, ancho+30, alto+30, pincel);
								}
			}
		}
	}
}
