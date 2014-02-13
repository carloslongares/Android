package com.example.centrospersonalprofesores;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModificaCentros extends Activity{
	SchemaCreator miBase;
	SQLiteDatabase db;
	Cursor c;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modificarcentro);
		App.setcontext(getApplicationContext());
		miBase=App.getInstance().getBaseDeDatos();
		db=miBase.getWritableDatabase();
		Bundle bundle = getIntent().getExtras();
	    String cod = bundle.getString("ID");
		final String[] clausula = new String[]{cod};
		String campos [] = new String[]{"cod_centro","tipo_centro","nombre","direccion","telefono","num_plazas"};
		c = db.query("centros",campos,"cod_centro=?",clausula,null,null,null);
		final EditText lblTipoCentro = (EditText)findViewById(R.id.tipoMod);
		final EditText lblNombre = (EditText)findViewById(R.id.nombreMod);
		final EditText lblDireccion = (EditText)findViewById(R.id.direccionMod);
		final EditText lblTelefono = (EditText)findViewById(R.id.telefonoMod);
		final EditText lblNumPlazas = (EditText)findViewById(R.id.numPlazasMod);
		final Button btnMod = (Button)findViewById(R.id.btnMod);
		final Button btnEle = (Button)findViewById(R.id.btnEle);
		
		
		if (c.moveToFirst()){
			do {
				
				String tipo= c.getString(1);
				String nombre= c.getString(2);
				String direccion =c.getString(3);
				String telefono = c.getString(4);
				int numPlazas = c.getInt(5);
				lblTipoCentro.setText(tipo);
				lblNombre.setText(nombre);
				lblDireccion.setText(direccion);
				lblTelefono.setText(telefono);
				lblNumPlazas.setText(String.valueOf(numPlazas));
		}while(c.moveToNext());
		
		}
		
		btnMod.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			ContentValues cv = new ContentValues();
			cv.put("tipo_centro", lblTipoCentro.getText().toString());
			cv.put("nombre", lblNombre.getText().toString());
			cv.put("direccion", lblDireccion.getText().toString());
			cv.put("telefono", lblTelefono.getText().toString());
			cv.put("num_plazas", Integer.parseInt(lblNumPlazas.getText().toString()));
			db.update("centros",cv,"cod_centro=?",clausula );
			Toast.makeText(getApplicationContext(), "Se ha modificado el registro", 20).show();
			db.close();
			finish();
			}
		});
		
		
		btnEle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {	
				db.delete("centros", "cod_centro=?", clausula);
				Toast.makeText(getApplicationContext(), "Se ha eliminado el registro", 20).show();
				db.close();
				finish();
			}
		});
		
	
	}
}
