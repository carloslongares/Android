package com.example.centrospersonalprofesores;

import java.util.ArrayList;

import com.example.centrospersonalprofesores.ConsultaPersonal.AdaptadorPersonal.ViewHolder;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class ConsultaProfesores extends Activity{
	SchemaCreator miBase;
	SQLiteDatabase db;
	Cursor c;
	ArrayList<Profesores> profesores ;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consultaprofesores);
		App.setcontext(getApplicationContext());
		miBase=App.getInstance().getBaseDeDatos();
		db=miBase.getWritableDatabase();
		profesores = new ArrayList<Profesores>();
		String campos [] = new String[]{"cod_centro","dni","apellidos","especialidad"};
		c = db.query("profesores",campos,null,null,null,null,null);
		
		
		Spinner spinerProfesores = (Spinner)findViewById(R.id.spinnerDeProfesores);
		
		if (c.moveToFirst()){
			do {
				int codigo = c.getInt(0);
				int dni= c.getInt(1);
				String apellidos= c.getString(2);
				String especialidad =c.getString(3);
				profesores.add(new Profesores(codigo,dni,apellidos,especialidad));
		}while(c.moveToNext());
		}
		AdaptadorProfesores adaptador = new AdaptadorProfesores(this);
		spinerProfesores.setAdapter(adaptador);
	}
	class AdaptadorProfesores extends ArrayAdapter<Profesores>{
		Activity context;
		
		class ViewHolder {
			TextView lblCodCentro;
	        TextView lblDni;
	        TextView lblApellidos;
	        TextView lblEspecialidad;
	    }
		
		public AdaptadorProfesores(Activity context) {
			super(context,R.layout.desgloseprofesores,profesores);
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
				item= inflater.inflate(R.layout.desgloseprofesores,null);
				
				holder = new ViewHolder();
		        holder.lblCodCentro = (TextView)item.findViewById(R.id.LblCodCentroProfesor);
		        holder.lblDni = (TextView)item.findViewById(R.id.LblDniProfesor);
		        holder.lblApellidos = (TextView)item.findViewById(R.id.LblApellidosProfesor);
		        holder.lblEspecialidad = (TextView)item.findViewById(R.id.LblEspecialidadProfesor);
		        item.setTag(holder);
			
			}else
				holder= (ViewHolder)item.getTag();
			
			
		      holder.lblCodCentro.setText("Codigo de centro: "+String.valueOf(profesores.get(position).getCodCentro()));
		      holder.lblDni.setText("DNI: "+profesores.get(position).getDni());
		      holder.lblApellidos.setText("Apellidos: "+profesores.get(position).getApellidos());
		      holder.lblEspecialidad.setText("Especialidad: "+profesores.get(position).getEspecialidad());
		      
			return (item);
		}
	}
}
