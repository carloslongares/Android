package com.example.viajes;





import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	public int precio;
	public boolean urgente = false;
	public String lugar;
	public String tarifa;
	public String decoracion ="";
	public int foto;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Spinner spinner = (Spinner)findViewById(R.id.spinner);
		final RadioButton rdNormal = (RadioButton)findViewById(R.id.normal);
		final RadioButton rdUrgente = (RadioButton)findViewById(R.id.urgente);
		final RadioGroup radioGroup = (RadioGroup)findViewById(R.id.grpRadio);
		final CheckBox checkRegalo = (CheckBox)findViewById(R.id.checkRegalo);
		final CheckBox checkTarjeta = (CheckBox)findViewById(R.id.checkTarjeta);
		final EditText peso = (EditText)findViewById(R.id.peso);
		final Button btnCalcula = (Button)findViewById(R.id.btnCalcula);
		final ImageView imagenPortada = (ImageView)findViewById(R.id.fotoPortada);
		
		
		AdaptadorDestinoSpinner miAdaptador= new AdaptadorDestinoSpinner (this);
		spinner.setAdapter(miAdaptador);
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View v,int position, long id) 
			{
				String zona=((Destino)parent.getAdapter().getItem(position)).getZona();
				String continente= ((Destino)parent.getAdapter().getItem(position)).getContinente();
				int precioElegido = ((Destino)parent.getAdapter().getItem(position)).getPrecio();
				int fotos = ((Destino)parent.getAdapter().getItem(position)).getFoto();
				if (zona.equalsIgnoreCase("Zona A")){
					precio = precioElegido;
					lugar= zona +" "+continente;
					foto=fotos;
					}
				
				if (zona.equalsIgnoreCase("Zona B")){
					precio = precioElegido;
					lugar= zona +" "+continente;
					foto=fotos;	
					Toast.makeText(getApplicationContext(), foto, 10).show();
				}
				
				if (zona.equalsIgnoreCase("Zona C")){
					precio = precioElegido;
					lugar= zona +" "+continente;	
					foto=fotos;
					Toast.makeText(getApplicationContext(), foto, 10).show();	
				}	
				}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
		
		
		radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId==R.id.urgente){
					tarifa="urgente";
					urgente= true;
					Toast.makeText(getApplicationContext(), "Urgente",100).show();
				}
				if(checkedId== R.id.normal){
					tarifa= "normal";
					urgente= false;
					Toast.makeText(getApplicationContext(), "normal",100).show();
					
				}
			}
		});
		
		checkRegalo.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked)
					decoracion +=" con caja de regalo"; 
			}
		});
		
		checkTarjeta.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked)
					decoracion +=" con dedicatoria"; 
			
			}
		});
		
		btnCalcula.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int kilos = Integer.parseInt(peso.getText().toString());
				int precioKilos =0;
				if (kilos < 5)
					precioKilos= kilos*1;
				if (kilos>6 && kilos <10)
					precioKilos=kilos*2;
				if(kilos>10)
					precioKilos=kilos*3;
				Log.e("", String.valueOf(kilos));
				
				if (urgente == true){
					precio = (precio+precioKilos)+(((precio+precioKilos)*30)/100);
				}
				if (urgente == false){
					precio = precio + precioKilos;
				Log.e("", String.valueOf(precioKilos));
				}
				
				
				Log.e("", String.valueOf(precio));
				
				Intent intent = new Intent (MainActivity.this,PantallaResultado.class);
				Bundle bundle = new Bundle();
				bundle.putString("ZONA",lugar);
				bundle.putString("TARIFA",tarifa);
				bundle.putString("PESO", peso.getText().toString());
				bundle.putString("DECORACION", decoracion);
				bundle.putInt("PRECIO", precio);
				bundle.putInt("FOTO", foto);
				intent.putExtras(bundle);
				startActivity(intent);
				
				
				precio =0;
				tarifa="";
			}
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId())
			{
			case R.id.dibujo:
				Intent intentBasicos =new Intent(MainActivity.this,Dibujo.class);
				startActivity(intentBasicos);
				return true;
			case R.id.acercaDe:
				Toast.makeText(getApplicationContext(), "Carlos Longares Alcala", 15).show();
				return true;

			default:
				return super.onOptionsItemSelected(item);

		
			}
		}

}
class AdaptadorDestinoSpinner extends ArrayAdapter<Destino>{
	
	static class ViewHolder {
		TextView lblZona;
		TextView lblContinente;
		TextView lblPrecio;
		ImageView lblFoto;
		
	}
	
	
	
	Activity miActividad;
	AdaptadorDestinoSpinner (Activity miActividad){
		super(miActividad,R.layout.desglose,destinos);
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
			item= inflater.inflate(R.layout.desglose,null);
			
			holder = new ViewHolder();
			holder.lblZona=(TextView)item.findViewById(R.id.campoZona);
			holder.lblContinente= (TextView)item.findViewById(R.id.campoContinente);
			holder.lblPrecio = (TextView)item.findViewById(R.id.campoPrecio);
			holder.lblFoto=(ImageView)item.findViewById(R.id.fotoSpinner);
			item.setTag(holder);
			
		}else
			holder= (ViewHolder)item.getTag();
		
		
		holder.lblZona.setText(destinos[position].getZona());
		holder.lblContinente.setText(destinos[position].getContinente());
		holder.lblPrecio.setText(Integer.toString(destinos[position].getPrecio()));
		holder.lblFoto.setImageResource(destinos[position].getFoto());
		
		return (item);
		}	
	
		private static Destino[] destinos = new Destino[]{
				new Destino("Zona A","Asia y Oceania",30,R.drawable.asia),
				new Destino("Zona B","Ameria y Africa",20,R.drawable.america),
				new Destino("Zona C","Europa",10,R.drawable.auropa)
		};
	
		
}