package com.example.viajes;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class PantallaCombio extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pantallacambio);
		
		final TextView lblDineroRecibido = (TextView)findViewById(R.id.dineroIntroducido);
		final TextView lblDineroAPagar = (TextView)findViewById(R.id.precioFinalCambio);
		final TextView lblDineroCambio = (TextView)findViewById(R.id.cambioDinero);
		Bundle bundle = getIntent().getExtras();
		float dineroRecibido=bundle.getFloat("DINERO");
		float dineroAPagar=bundle.getFloat("PRECIO");
		
		float dineroCambio = dineroRecibido - dineroAPagar;
		
		lblDineroRecibido.setText(String.valueOf(dineroRecibido));
		lblDineroAPagar.setText(String.valueOf(dineroAPagar));
		
		float billete5=0;
		float billete10=0;
		float billete20=0;
		float billete50=0;
		float billete100=0;
		float billete200=0;
		float billete500=0;
		
		 	billete500=(int)(dineroCambio/500.0);
	        dineroCambio-=billete500*500;
	        billete200=(int)(dineroCambio/200.0);
	        dineroCambio-=billete200*200;
	        billete100=(int)(dineroCambio/100.0);
	        dineroCambio-=billete100*100;
	        billete50=(int)(dineroCambio/50.0);
	        dineroCambio-=billete50*50;
	        billete20=(int)(dineroCambio/20.0);
	        dineroCambio-=billete20*20;
	        billete10=(int)(dineroCambio/10.0);
	        dineroCambio-=billete10*10;
	    
	        
	        
	        String resultado =" billete500: "+billete500+ 
	        		"\n billetes de 200: "+billete200+
	        		"\n billetes de 100:  "+billete100+
	        		"\n billetes de 50: "+billete50+
	        		"\n billetes de 20: "+billete20+
	        		"\n billetes de 20: "+billete10+
	        		"\n monedas restantes: "+dineroCambio+" euros.";
		
	        lblDineroCambio.setText(String.valueOf(resultado));
			
	    
		}
			
	}

