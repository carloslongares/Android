package com.example.viajes;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PantallaResultado extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pantallaresultado);
		
		final Button btnCal = (Button)findViewById(R.id.btnCambio);		
		final TextView lblZona = (TextView)findViewById(R.id.resultadoZona);
		final TextView lblTarifa = (TextView)findViewById(R.id.resultadoTarifa);
		final TextView lblPeso = (TextView)findViewById(R.id.resultadoPesp);
		final TextView lvlDecoracion = (TextView)findViewById(R.id.resultadoDecoracion);
		final TextView lvlCoste = (TextView)findViewById(R.id.resultadoCosteFinal);
		final ImageView lblFoto = (ImageView)findViewById(R.id.fotoSpinner);
		final EditText lbldinero = (EditText)findViewById(R.id.dinero);
		final Bundle bundle = getIntent().getExtras();
		
		lblZona.setText("Zona: " + bundle.getString("ZONA"));
		lblTarifa.setText("Tarifa: " + bundle.getString("TARIFA"));
		lblPeso.setText("Peso: "+bundle.getString("PESO"));
		lvlDecoracion.setText("Decoracion: "+bundle.getString("DECORACION"));
		lvlCoste.setText("Coste final "+String.valueOf(bundle.getInt("PRECIO")));
		lblFoto.setImageResource(bundle.getInt("FOTO"));
	
		btnCal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				float dineroIntroducido = Float.parseFloat(lbldinero.getText().toString());
				float precio = Float.parseFloat( String.valueOf(bundle.getInt("PRECIO")));
				Intent intent = new Intent (PantallaResultado.this,PantallaCombio.class);
				
				if (precio > dineroIntroducido) {
					Toast.makeText(getApplicationContext(), "EL dinero introducido no es suficiente para pagar!!!",50 ).show();
				}else{
				Bundle bundle = new Bundle();
				bundle.putFloat("DINERO", dineroIntroducido);
				bundle.putFloat("PRECIO", precio);
				intent.putExtras(bundle);
				startActivity(intent);
				}
			}
		});
	
	}
}
