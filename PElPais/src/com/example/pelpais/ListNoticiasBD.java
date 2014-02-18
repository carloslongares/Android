package com.example.pelpais;



import java.util.ArrayList;





import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListNoticiasBD extends Activity{
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	     setContentView(R.layout.listanoticias);
	     
	     SchemaCreator schema = new SchemaCreator(this, "ElPais", null, 1);
	     SQLiteDatabase db = schema.getWritableDatabase();
	     String [] columns = {"noticia"};
	     ArrayList<String> listaNoticias = new ArrayList<String>();
	     Cursor c = db.query("noticias", columns, null, null, null, null, null);
	     if (c.moveToFirst()){
				do {
					String noticia= c.getString(0);
					listaNoticias.add(new String(noticia));				
			}while(c.moveToNext());
		 }
	     
	     ListView lista = (ListView)findViewById(R.id.miLista);
	     ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listaNoticias);
	     lista.setAdapter(adaptador);
	     
	 }
}