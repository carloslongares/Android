package com.example.viajes;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PantallaResultado extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pantallaresultado);
		
		final TextView lblZona = (TextView)findViewById(R.id.resultadoZona);
		final TextView lblTarifa = (TextView)findViewById(R.id.resultadoTarifa);
		final TextView lblPeso = (TextView)findViewById(R.id.resultadoPesp);
		final TextView lvlDecoracion = (TextView)findViewById(R.id.resultadoDecoracion);
		final TextView lvlCoste = (TextView)findViewById(R.id.resultadoCosteFinal);
		
		Bundle bundle = getIntent().getExtras();
		
		lblZona.setText("Zona: " + bundle.getString("ZONA"));
		lblTarifa.setText("Tarifa: " + bundle.getString("TARIFA"));
		lblPeso.setText("Peso: "+bundle.getString("PESO"));
		lvlDecoracion.setText("Decoracion: "+bundle.getString("DECORACION"));
		lvlCoste.setText("Coste final "+String.valueOf(bundle.getInt("PRECIO")));
	}
}
