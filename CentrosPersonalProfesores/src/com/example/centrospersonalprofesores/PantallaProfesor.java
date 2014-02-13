package com.example.centrospersonalprofesores;


import java.util.ArrayList;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class PantallaProfesor extends Activity {
	SchemaCreator miBase;
	SQLiteDatabase db;
	int codCentro;
	int dni;
	String apellidos;
	String especialidad;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pantallaprofesor);
		App.setcontext(getApplicationContext());
		miBase=App.getInstance().getBaseDeDatos();
		db= miBase.getWritableDatabase();
		String camposCentro[] = new String[]{"cod_centro"};
		String camposPersonal[] = new String[]{"dni"};
		final ArrayList<Integer> modeloCod =miBase.rellenaModeloPrimary(db, "centros",camposCentro);
		final ArrayList<Integer> modeloDni = miBase.rellenaModeloPrimary(db, "personal", camposPersonal);
		
		final Spinner spinnerCodigo =(Spinner)findViewById(R.id.spinnerCodProfesor);
		final Spinner spinnerDni = (Spinner)findViewById(R.id.spinnerDniProfesor);
		final EditText campoApellidos= (EditText)findViewById(R.id.apellidosPro);
		final EditText campoEspecialidad =(EditText)findViewById(R.id.especialidad);
		final Button btnInserta= (Button)findViewById(R.id.insertaProfesor);
	
		ArrayAdapter<Integer> adaptadorCod = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item,modeloCod);
		spinnerCodigo.setAdapter(adaptadorCod);
		
		ArrayAdapter<Integer> adaptadorDni = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item,modeloDni);
		spinnerDni.setAdapter(adaptadorDni);
		
		spinnerCodigo.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View v,int position, long id) 
			{
				int codSelec = modeloCod.get(position);
				codCentro= codSelec;
				}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
		
		
		spinnerDni.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View v,int position, long id) 
			{
				int dniSelec = modeloDni.get(position);
				dni= dniSelec;
				}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
		
		
		btnInserta.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				apellidos = campoApellidos.getText().toString();
				especialidad = campoEspecialidad.getText().toString();
				db.execSQL("INSERT INTO profesores VALUES("+
						codCentro +","+
						dni+","+
						"'"+apellidos+"',"+
						"'"+especialidad+"');");
				
				Toast.makeText(getApplicationContext(), "Profesor insertado", 10).show();
			}
		});
	
	}
	
	protected void onPause(){
		super.onPause();
		db.close();
	}

}
