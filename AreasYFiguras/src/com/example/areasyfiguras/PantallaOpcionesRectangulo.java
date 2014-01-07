package com.example.areasyfiguras;

import com.example.areasyfiguras.AdaptadorColorSpinner.ViewHolder;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
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

public class PantallaOpcionesRectangulo extends Activity{

	
	
	protected void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.opcionesrectangulo);
			Spinner spinner = (Spinner)findViewById(R.id.listaRectangulo);
			AdaptadorColorRectanguloSpinner miAdaptodor = new AdaptadorColorRectanguloSpinner(this);
			spinner.setAdapter(miAdaptodor);
			
			final Button btnCalcula = (Button)findViewById(R.id.btnRectCalcula);
			final Button btnDibuja = (Button)findViewById(R.id.btnRectDibuja);
			final EditText campoAncho = (EditText)findViewById(R.id.campoAncho);
			final EditText campoAlto = (EditText)findViewById(R.id.campoAlto);
			final TextView resultado =(TextView)findViewById(R.id.resultRectArea);
			final Bundle bundle = new Bundle();
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
				
				public void onClick(View v) {
					
					String ancho = campoAncho.getText().toString();
					String alto= campoAlto.getText().toString();
					

					if (ancho.equals("") || alto.equals(""))
					{
						
					}
					else{
						bundle.putString("FIGURA","rectangulo");
						bundle.putString("ANCHO", ancho);
						bundle.putString("ALTO", alto);
						Intent intent = new Intent(PantallaOpcionesRectangulo.this,Dibujo.class);
						intent.putExtras(bundle);
						
						startActivity(intent);
					}
				}
			});
		
			btnCalcula.setOnClickListener(new OnClickListener() {
				
				
				public void onClick(View v) {
					int medida =Integer.parseInt (campoAncho.getText().toString())*Integer.parseInt(campoAlto.getText().toString());
					resultado.setText(String.valueOf(medida));
					
				}
			});
		}
}


class AdaptadorColorRectanguloSpinner extends ArrayAdapter<ColorFigura>{
	
	static class ViewHolder {
		ImageView lblfoto;
	}
	
	
	
	Activity miActividad;
	AdaptadorColorRectanguloSpinner (Activity miActividad){
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


	


