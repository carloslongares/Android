package com.example.areasyfiguras;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final ListView miLista = (ListView)findViewById(R.id.lista);
		AdaptadorFigura miAdapatador = new AdaptadorFigura(this);
		
		miLista.setAdapter(miAdapatador);
		
		miLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent,  android.view.View v, int position, long id){
				String tipo=((Figura)parent.getAdapter().getItem(position)).getTipo();
				Intent intentCirculo;
				Intent intentCuadrado;
				Intent intentRectangulo;
				
				if (tipo.equalsIgnoreCase("cuadrado")){
					intentCuadrado = new Intent(MainActivity.this,PantallaOpcionesCuadrado.class);
					startActivity(intentCuadrado);
				}
				
				if (tipo.equalsIgnoreCase("rectangulo")){
					intentRectangulo = new Intent (MainActivity.this,PantallaOpcionesRectangulo.class);
					startActivity(intentRectangulo);
				}
				
				if (tipo.equalsIgnoreCase("circulo")){
					intentCirculo = new Intent(MainActivity.this,PantallaOpcionesCirculo.class);
					startActivity(intentCirculo);
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


class AdaptadorFigura extends ArrayAdapter<Figura>{
	
	static class ViewHolder {
		TextView lblTipo;
		ImageView lblFoto;
	}
	
	
	
	Activity miActividad;
	
	AdaptadorFigura (Activity miActividad){
		super(miActividad,R.layout.desmilista,figuras);
		this.miActividad=miActividad;
	}
	



	public  View getView (int position,View convertView,ViewGroup Parent){
		View item=convertView;
		ViewHolder holder;
		
		if(item==null){
			
			LayoutInflater inflater= miActividad.getLayoutInflater();
			item= inflater.inflate(R.layout.desmilista,null);
			
			holder = new ViewHolder();
			holder.lblTipo=(TextView)item.findViewById(R.id.campoTipo);
			holder.lblFoto= (ImageView)item.findViewById(R.id.campoFoto);
			item.setTag(holder);
			
		}else
			holder= (ViewHolder)item.getTag();
		
		
		holder.lblTipo.setText(figuras[position].getTipo());
		holder.lblFoto.setImageResource(figuras[position].getFoto());
		
		
		return item;
		}
	
	private static Figura[] figuras = new Figura[]{
				new Figura("Cuadrado",R.drawable.cuadrado),
				new Figura("Rectangulo",R.drawable.rectangulo),
				new Figura ("Circulo",R.drawable.circulo)
	};
	
		
}
