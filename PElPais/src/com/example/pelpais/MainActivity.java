package com.example.pelpais;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Button boton;
	private TextView texto;
	SchemaCreator schema;
	SQLiteDatabase db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        schema = new SchemaCreator(this, "ElPais", null, 1);
        db = schema.getWritableDatabase();
        boton = (Button)findViewById(R.id.boton1);
        Button  btnLista = (Button)findViewById(R.id.pasar);
        texto = (TextView)findViewById(R.id.texto1);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //Escuchador del botón. Acciones en caso de pulsar el botón
        boton.setOnClickListener(new OnClickListener() {
        	public void onClick(View view) {
        		try {
        			String noticias = buscarNoticias();
        			texto.append(noticias);
        		} catch (Exception e) {
        			texto.append(e.toString());
        			e.printStackTrace();
        		}
        	}
        });
        
        btnLista.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,ListNoticiasBD.class);
				startActivity(intent);
			}
		});
        
    }

    private String buscarNoticias() throws Exception {
    	String salida="";
    	int i=0, j=0;
	
    	//Dirección de la página (RSS) que queremos obtener (Corresponde a titulares del periódico)
    	URL url = new URL("http://www.elpais.com/rss/feed.html?feedId=1022");
    	//Conexión de tipo HTTP
    	HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
    	//Añadimos una cabecera HTTP para que identificarnos y evitar obtener un error de aquellos
    	//servidores que prohiben la respuesta a aquellos clientes que no se identifican.
    	conexion.setRequestProperty("User-Agent", "Mozilla/5.0" +
    								" (Linux; Android 1.5; es-ES) Ejemplo HTTP");
	
    	if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
    		BufferedReader lector = new BufferedReader (
    				new InputStreamReader(conexion.getInputStream()));
    		String linea = lector.readLine();
    		while (linea != null) {
    			//Si encontramos la etiqueta <title> podemos recuperar la noticia
    			if (linea.indexOf("<title>") >= 0) {
    				i = linea.indexOf("<title>")+16;
    				j = linea.indexOf("</title>")-3;
    				String registro =linea.substring(i,j);
    				db.execSQL("INSERT INTO noticias (noticia) VALUES ('"+registro+"')");
    				salida += linea.substring(i,j);
    				salida += "\n----------------\n";
    			}
    			//Leemos la siguiente línea
    			linea = lector.readLine();
    		}
    		lector.close();
    	} else {
    		salida = "No encontrado";
    	}
    	conexion.disconnect();
	
    	return salida;
    }
}
