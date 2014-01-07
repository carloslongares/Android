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

public class PantallaOpcionesCuadrado extends Activity {


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.opcionescuadrado);
		Spinner spinner = (Spinner)findViewById(R.id.listaCuadrado);
		AdaptadorColorCuadradoSpinner miAdaptador = new AdaptadorColorCuadradoSpinner(this);
		spinner.setAdapter(miAdaptador);
		
		final TextView resultado =(TextView)findViewById(R.id.resultCuaArea);
		final Button btnCuaDibuja =(Button)findViewById(R.id.btnCuaDibuja);
		final Button btnCuaCalcula =(Button)findViewById(R.id.btnCuaCalcula);
		final EditText lado =(EditText)findViewById(R.id.campoLado);
		final Intent intent = new Intent (PantallaOpcionesCuadrado.this,Dibujo.class);
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
		
		
		
		btnCuaDibuja.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String medida = lado.getText().toString();

				if (medida.equals(""))
				{	
				}
				else
				{
					bundle.putString("LADO", medida);
					bundle.putString("FIGURA","cuadrado");
					intent.putExtras(bundle);
					startActivity(intent);
				}
			}
		});
		
		btnCuaCalcula.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String Medida = lado.getText().toString();
				double calculoMedida = Math.pow(Integer.parseInt(Medida), 2);
				resultado.setText(String.valueOf(calculoMedida));
				
			}
		});
	
	}
}

class AdaptadorColorCuadradoSpinner extends ArrayAdapter<ColorFigura>{
	
	static class ViewHolder {
		ImageView lblfoto;
	}
	
	
	
	Activity miActividad;
	AdaptadorColorCuadradoSpinner (Activity miActividad){
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


	


