package com.example.centrospersonalprofesores;

import java.util.ArrayList;



import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class ConsultaCentros extends Activity{
	SchemaCreator miBase;
	SQLiteDatabase db;
	Cursor c;
	int check =0;
	ArrayList<Centros> centros ;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consultacentros);
		App.setcontext(getApplicationContext());
		miBase=App.getInstance().getBaseDeDatos();
		db=miBase.getWritableDatabase();
		
		final Spinner spinerCentros =(Spinner)findViewById(R.id.spinnerDeCentros);
	
		centros = new ArrayList<Centros>();
		String campos [] = new String[]{"cod_centro","tipo_centro","nombre","direccion","telefono","num_plazas"};
		c = db.query("centros",campos,null,null,null,null,null);
		
		if (c.moveToFirst()){
			do {
				
				int codigo = c.getInt(0);
				String tipo= c.getString(1);
				String nombre= c.getString(2);
				String direccion =c.getString(3);
				String telefono = c.getString(4);
				int numPlazas = c.getInt(5);
				
				centros.add(new Centros(codigo,tipo,nombre,direccion,telefono,numPlazas));
				
		}while(c.moveToNext());
		
		}
		db.close();
		
		AdaptadorCentros adaptador = new AdaptadorCentros(this);
		spinerCentros.setAdapter(adaptador);
		
		
		spinerCentros.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				check+= check+1;
				if(check>1){
				String codigo =String.valueOf(centros.get(position).getCodCentro());
				Bundle bundle = new Bundle();
				bundle.putString("ID", codigo);
				Intent intent = new Intent(ConsultaCentros.this,ModificaCentros.class);
				intent.putExtras(bundle);
				startActivity(intent);
				finish();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	
	
class AdaptadorCentros extends ArrayAdapter<Centros>{
	Activity context;
	
	class ViewHolder {
		TextView lblCodCentro;
        TextView lblTipoCentro;
        TextView lblNombre;
        TextView lblDireccion;
        TextView lblTelefono;
        TextView lblNumPlazas;
	}
	
	public AdaptadorCentros(Activity context) {
		super(context,R.layout.desglosecentros,centros);
		this.context=context;
	}

	public View getDropDownView(int position,View convertView,ViewGroup Parent){
		return getView( position,convertView,Parent);
	}

	public  View getView (int position,View convertView,ViewGroup Parent){
		View item=convertView;
		ViewHolder holder;
		
		if(item==null){
			
			LayoutInflater inflater= context.getLayoutInflater();
			item= inflater.inflate(R.layout.desglosecentros,null);
			
			holder = new ViewHolder();
	        holder.lblCodCentro = (TextView)item.findViewById(R.id.LblCodCentroCentro);
	        holder.lblTipoCentro = (TextView)item.findViewById(R.id.LblTipoCentroCentro);
	        holder.lblNombre = (TextView)item.findViewById(R.id.LblNombreCentrol);
	        holder.lblDireccion = (TextView)item.findViewById(R.id.LblDireccionCentro);
	        holder.lblTelefono = (TextView)item.findViewById(R.id.LblTelefonoCentro);
	        holder.lblNumPlazas = (TextView)item.findViewById(R.id.LblNumPlazasCentro);
    			item.setTag(holder);
		
		}else
			holder= (ViewHolder)item.getTag();
		
		
	      holder.lblCodCentro.setText("Codigo de centro: "+String.valueOf(centros.get(position).getCodCentro()));
	      holder.lblTipoCentro.setText("Tipo de centro: "+centros.get(position).getTipoCentro());
	      holder.lblNombre.setText("Nombre del centro: "+centros.get(position).getNombre());
	      holder.lblDireccion.setText("Direccion del centro: "+centros.get(position).getDireccion());
	      holder.lblTelefono.setText("Numero de telefono: "+centros.get(position).getTelefono());
	      holder.lblNumPlazas.setText("Numero de plazas: "+String.valueOf(centros.get(position).getNumPlazas()));
	      
	
		return (item);
	}


}
}
