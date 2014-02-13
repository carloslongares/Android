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

public class PantallaPersonal extends Activity {
	SchemaCreator miBase;
	SQLiteDatabase db;
	int codCentro;
	int dni;
	String apellidos;
	String funcion;
	int salario;
	
	protected void onCreate(Bundle savedInstanceState) {
		App.setcontext(getApplicationContext());
		miBase=App.getInstance().getBaseDeDatos();
		db= miBase.getWritableDatabase();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pantallapersonal);
		final Spinner spinerPersonal = (Spinner)findViewById(R.id.spinnerCodCenPersonal);
		final EditText campoDni = (EditText)findViewById(R.id.dni);
		final EditText campoApellidos = (EditText)findViewById(R.id.apellidos);
		final EditText campoFuncion = (EditText)findViewById(R.id.funcion);
		final EditText campoSalario = (EditText)findViewById(R.id.salario);
		final Button btnInserta = (Button)findViewById(R.id.insertPersonal);
		String camposCentro[] = new String[]{"cod_centro"};
		final ArrayList<Integer> modelo = miBase.rellenaModeloPrimary(db, "centros", camposCentro);
		ArrayAdapter<Integer> adaptador = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item,modelo);
		spinerPersonal.setAdapter(adaptador);
		
		spinerPersonal.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View v,int position, long id) 
			{
				int codSelec = modelo.get(position);
				codCentro= codSelec;
				
				}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
		btnInserta.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dni = Integer.parseInt( campoDni.getText().toString() );
				apellidos = campoApellidos.getText().toString();
				funcion = campoFuncion.getText().toString();
				salario= Integer.parseInt( campoSalario.getText().toString() );
				
				db.execSQL("INSERT INTO personal VALUES("+
						codCentro +","+
						dni+","+
						"'"+apellidos+"',"+
						"'"+funcion+"',"+
						salario+");");
				
				Toast.makeText(getApplicationContext(), "Personal insertado", 10).show();
			}
		});
		
		
		}
	
	protected void onPause(){
		super.onPause();
		db.close();
	}
}
