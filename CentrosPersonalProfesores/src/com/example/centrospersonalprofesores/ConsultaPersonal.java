package com.example.centrospersonalprofesores;

import java.util.ArrayList;

import com.example.centrospersonalprofesores.ConsultaCentros.AdaptadorCentros.ViewHolder;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class ConsultaPersonal extends Activity {
	SchemaCreator miBase;
	SQLiteDatabase db;
	Cursor c;
	ArrayList<Personal> personal ;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consultapersonal);
		App.setcontext(getApplicationContext());
		miBase=App.getInstance().getBaseDeDatos();
		db=miBase.getWritableDatabase();
		personal = new ArrayList<Personal>();
		String campos [] = new String[]{"cod_centro","dni","apellidos","funcion","salario"};
		c = db.query("personal",campos,null,null,null,null,null);
		final Spinner spinerPersonal =(Spinner)findViewById(R.id.spinnerDePersonal);
	
		if (c.moveToFirst()){
			do {
				
				int codigo = c.getInt(0);
				int dni= c.getInt(1);
				String apellidos= c.getString(2);
				String funcion =c.getString(3);
				int salario = c.getInt(4);
				personal.add(new Personal(codigo,dni,apellidos,funcion,salario));
		}while(c.moveToNext());
		}
		AdaptadorPersonal adpatador = new AdaptadorPersonal(this);
		spinerPersonal.setAdapter(adpatador);
	}
	
	
class AdaptadorPersonal extends ArrayAdapter<Personal>{
	Activity context;
	
	class ViewHolder {
		TextView lblCodCentro;
        TextView lblDni;
        TextView lblApellidos;
        TextView lblFuncion;
        TextView lblSalario;
    }
	
	public AdaptadorPersonal(Activity context) {
		super(context,R.layout.desglosepersonal,personal);
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
			item= inflater.inflate(R.layout.desglosepersonal,null);
			
			holder = new ViewHolder();
	        holder.lblCodCentro = (TextView)item.findViewById(R.id.LblCodCentroPersonal);
	        holder.lblDni = (TextView)item.findViewById(R.id.LblDniPersonal);
	        holder.lblApellidos = (TextView)item.findViewById(R.id.LblApellidosPersonal);
	        holder.lblFuncion = (TextView)item.findViewById(R.id.LblFuncionPersonal);
	        holder.lblSalario = (TextView)item.findViewById(R.id.LblSalarioPersonal);
	        item.setTag(holder);
		
		}else
			holder= (ViewHolder)item.getTag();
		
		
	      holder.lblCodCentro.setText("Codigo de centro: "+String.valueOf(personal.get(position).getCodCentro()));
	      holder.lblDni.setText("DNI: "+personal.get(position).getDni());
	      holder.lblApellidos.setText("Apellidos: "+personal.get(position).getApellidos());
	      holder.lblFuncion.setText("Funcion: "+personal.get(position).getFuncion());
	      holder.lblSalario.setText("Salario: "+personal.get(position).getSalario());
	
		return (item);
	}
//	        holder.lblCodCentro = (TextView)item.findViewById(R.id.LblCodCentroCentro);
//	        holder.lblTipoCentro = (TextView)item.findViewById(R.id.LblTipoCentroCentro);
//	        holder.lblNombre = (TextView)item.findViewById(R.id.LblNombreCentrol);
//	        holder.lblDireccion = (TextView)item.findViewById(R.id.LblDireccionCentro);
//	        holder.lblTelefono = (TextView)item.findViewById(R.id.LblTelefonoCentro);
//	        holder.lblNumPlazas = (TextView)item.findViewById(R.id.LblNumPlazasCentro);
    
        
//        holder.lblCodCentro.setText(centros.get(position).getCodCentro());
//        holder.lblTipoCentro.setText(centros.get(position).getTipoCentro());
//        holder.lblNombre.setText(centros.get(position).getNombre());
//        holder.lblDireccion.setText(centros.get(position).getDireccion());
//        holder.lblTelefono.setText(centros.get(position).getTelefono());
//        holder.lblNumPlazas.setText(centros.get(position).getNumPlazas());
//        Log.e("getter", centros.get(position).getDireccion());
// 

}
}


