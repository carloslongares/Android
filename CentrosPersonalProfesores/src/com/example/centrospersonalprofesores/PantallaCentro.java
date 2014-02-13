package com.example.centrospersonalprofesores;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PantallaCentro extends Activity {
	SchemaCreator miBase;
	SQLiteDatabase db;
	int codCentro;
	String tipoCentro;
	String nombre;
	String direccion;
	String telefono;
	int numPlazas;
	protected void onCreate(Bundle savedInstanceState) {
		App.setcontext(getApplicationContext());
		miBase=App.getInstance().getBaseDeDatos();
		db= miBase.getWritableDatabase();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pantallacentro);
		final EditText campoCod= (EditText)findViewById(R.id.codCentro);
		final EditText campoTipo= (EditText)findViewById(R.id.tipoCentro);
		final EditText campoNom= (EditText)findViewById(R.id.nombre);
		final EditText campoDir= (EditText)findViewById(R.id.direccion);
		final EditText campoTel= (EditText)findViewById(R.id.telefono);
		final EditText campoNumPla= (EditText)findViewById(R.id.numPlazas);
		final Button btnInserta = (Button)findViewById(R.id.insertaCentro);
		
		btnInserta.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				codCentro =Integer.parseInt( campoCod.getText().toString() );
				tipoCentro = campoTipo.getText().toString();
				nombre= campoNom.getText().toString();
				direccion = campoDir.getText().toString();
				telefono = campoTel.getText().toString();
				numPlazas= Integer.parseInt( campoNumPla.getText().toString() );
				
				db.execSQL("INSERT INTO centros VALUES("+
						codCentro +","+
						"'"+tipoCentro+"',"+
						"'"+nombre+"',"+
						"'"+direccion+"',"+
						"'"+telefono+"',"+
						numPlazas+");");
				
				Toast.makeText(getApplicationContext(), "Centro insertado", 20).show();
			}
		});
		}
	
	protected void onPause(){
		super.onPause();
		db.close();
	}
}
