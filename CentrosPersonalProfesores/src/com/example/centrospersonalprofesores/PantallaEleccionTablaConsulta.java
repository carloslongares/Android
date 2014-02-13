package com.example.centrospersonalprofesores;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class PantallaEleccionTablaConsulta extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pantallaeleccionconsulta);
		String modelo [] = new String[]{"Centros (ver , modificar y eliminar)","Personal (ver)","Profesores (ver)"};
		final ListView miLista = (ListView)findViewById(R.id.listaTablas);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,modelo);
		miLista.setAdapter(adaptador);
		miLista.setOnItemClickListener(new OnItemClickListener() {
		    @Override
		    public void onItemClick(AdapterView<?> a, View v, int position, long id) {
		    	if (position == 0){
		    		Intent intent = new Intent(PantallaEleccionTablaConsulta.this,ConsultaCentros.class);
		    		startActivity(intent);
		    	}
		    	
		    	if (position == 1){
		    		Intent intent = new Intent(PantallaEleccionTablaConsulta.this,ConsultaPersonal.class);
		    		startActivity(intent);
		    	}
		    	
		    	if (position == 2){
		    		Intent intent = new Intent(PantallaEleccionTablaConsulta.this,ConsultaProfesores.class);
		    		startActivity(intent);
		    	}
		 	
		    }
		});
	}
}