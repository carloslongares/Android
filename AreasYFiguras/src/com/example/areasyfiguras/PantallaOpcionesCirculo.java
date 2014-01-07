package com.example.areasyfiguras;



import java.util.LinkedList;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PantallaOpcionesCirculo extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.opcionescirculo);
		Spinner spinner =(Spinner)findViewById(R.id.listaCirculo);
		
		AdaptadorColorSpinner miAdapatador = new AdaptadorColorSpinner(this);
		spinner.setAdapter(miAdapatador);
		final Intent intent = new Intent(PantallaOpcionesCirculo.this,Dibujo.class);
		final Bundle bundle = new Bundle();	
		
		final Button btnDibuja = (Button)findViewById(R.id.btnDibu);
		final EditText radio = (EditText)findViewById(R.id.campoRadio);
		
		final Button btnCalcula = (Button)findViewById(R.id.btnArea);
		final TextView resultado = (TextView)findViewById(R.id.areaResultado);
		
		
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent,  android.view.View v, int position, long id) {
				int color = ((ColorFigura)parent.getAdapter().getItem(position)).getColor();
				bundle.putInt("COLOR", color);
			
			
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
			});
		

		
		btnDibuja.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String campoRadio = radio.getText().toString();
				
				if (campoRadio.equals(""))
				{
			
				}
				else{
					String resultadoArea;
					double resultadoCalculado;
					
					double Valradio = Integer.parseInt(radio.getText().toString());
				
					resultadoCalculado= Math.PI*Math.pow(Valradio, 2);
					resultadoArea=String.valueOf(resultadoCalculado);
					
				
					bundle.putString("FIGURA","circulo");
					bundle.putString("RADIO", campoRadio);
					bundle.putString("AREA", resultadoArea);
					intent.putExtras(bundle);
					
					startActivity(intent);
				}
				}
			});
		}


}

class AdaptadorColorSpinner extends ArrayAdapter<ColorFigura>{
	
	static class ViewHolder {
		ImageView lblfoto;
	}
	
	
	
	Activity miActividad;
	AdaptadorColorSpinner (Activity miActividad){
		super(miActividad,R.layout.desmilistacolor,colores);
		this.miActividad=miActividad;
	}
	

	public View getDropDownView(int position,View convertView,ViewGroup Parent){
		return getView( position,convertView,Parent);
	}

	public  View getView (int position,View convertView,ViewGroup Parent){
		View item=convertView;
		ViewHolder holder;
		
		if(item==null){
			
			LayoutInflater inflater= miActividad.getLayoutInflater();
			item= inflater.inflate(R.layout.desmilistacolor,null);
			
			holder = new ViewHolder();

			holder.lblfoto = (ImageView)item.findViewById(R.id.fotoColor);
			item.setTag(holder);
			
		}else
			holder= (ViewHolder)item.getTag();
		

		holder.lblfoto.setImageResource(colores[position].getFoto());
		
		return (item);
		}	
	
		static ColorFigura[] colores = new ColorFigura[]{new ColorFigura(R.drawable.rojo,Color.RED),
												   new ColorFigura(R.drawable.azul,Color.BLUE),
												   new ColorFigura(R.drawable.verde, Color.GREEN),
												   new ColorFigura(R.drawable.amarillo, Color.YELLOW),
												   new ColorFigura(R.drawable.gris, Color.GRAY),
												   new ColorFigura(R.drawable.morado, Color.rgb(87, 35, 100)),
												   new ColorFigura(R.drawable.narnaja, Color.rgb(255, 102, 0)),
												   new ColorFigura(R.drawable.negro, Color.BLACK),
												   new ColorFigura(R.drawable.rosa,Color.MAGENTA)};
		
}


	

