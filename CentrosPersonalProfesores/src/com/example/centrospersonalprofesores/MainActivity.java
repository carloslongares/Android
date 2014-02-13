package com.example.centrospersonalprofesores;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	SchemaCreator centroProBD;
	SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		App.setcontext(getApplicationContext());
		centroProBD =App.getInstance().getBaseDeDatos();
		ArrayList<String> modelo = new ArrayList<String>();
		modelo.add("Restaurar base de datos");
		modelo.add("Insertar un centro");
		modelo.add("Insertar personal ");
		modelo.add("Insertar un profesor");
		modelo.add("Consultar base de datos\n(modifica y elimina centros)");
		modelo.add("Estadisticas");
		modelo.add("Salir");
		ListView miLista =(ListView)findViewById(R.id.lista);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,modelo);
		miLista.setAdapter(adaptador);
		
		miLista.setOnItemClickListener(new OnItemClickListener() {
		    @Override
		    public void onItemClick(AdapterView<?> a, View v, int position, long id) {
		 
		    	if(position == 0){
		    		centroProBD.rellenaCentros(db);
					centroProBD.rellenaProfesores(db);
					centroProBD.rellenaPersonal(db);
					Toast.makeText(getApplicationContext(), "Se han restaurado las tablas a su estado de creacion", 20).show();
		    	}
		    
		    	if (position == 1){
		    		Intent intent = new Intent(MainActivity.this,PantallaCentro.class);
					startActivity(intent);
		    	}
		    	
		    	if(position == 2){
		    		Intent intent = new Intent(MainActivity.this,PantallaPersonal.class);
					startActivity(intent);				
		    	}
		    	
		    	if(position == 3){
		    		Intent intent = new Intent(MainActivity.this,PantallaProfesor.class);
					startActivity(intent);					
		    	}
		    	
		    	if(position == 4){
		    		Intent intent = new Intent(MainActivity.this,PantallaEleccionTablaConsulta.class);
		    		startActivity(intent);
		    	}
		    	
		    	if (position == 6){
		    		db.close();
		    		finish();
		    	}
		    	
		    }
		});
		
	}
	
	protected void onPause(){
		super.onPause();
		db.close();
	}
	
	protected void onResume(){
		super.onResume();
		db = centroProBD.getWritableDatabase();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
