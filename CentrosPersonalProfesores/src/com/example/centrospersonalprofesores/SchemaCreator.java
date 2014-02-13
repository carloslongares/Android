package com.example.centrospersonalprofesores;

import java.util.ArrayList;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SchemaCreator extends SQLiteOpenHelper {

	
	
	public SchemaCreator(Context context, String name, CursorFactory factory,
			int ver) {
		super(context, name, factory, ver);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	//Crea la tabla centro en primer lugar
	
		db.execSQL("CREATE TABLE IF NOT EXISTS centros " +
				"( cod_centro   SMALLINT NOT NULL," +
				"tipo_centro  CHAR(1)," +
				"nombre  VARCHAR(30)," +
				" direccion VARCHAR(26)," +
				" telefono VARCHAR(10)," +
				" num_plazas SMALLINT  UNSIGNED," +
				" PRIMARY KEY (cod_centro));");
	
	//Crea tabla perosnal
		db.execSQL("CREATE TABLE IF NOT EXISTS personal (" +
				"cod_centro   SMALLINT NOT NULL," +
				" dni INT UNSIGNED NOT NULL," +
				" apellidos  VARCHAR(30)," +
				" funcion  VARCHAR(15)," +
				" salario FLOAT(7,2)," +
				" PRIMARY KEY (dni)," +
				" FOREIGN KEY (cod_centro) REFERENCES centros (cod_centro));");
		
	//Crea tabla profesores
		db.execSQL("CREATE TABLE IF NOT EXISTS profesores (" +
				" cod_centro   SMALLINT NOT NULL, " +
				"dni  INT UNSIGNED NOT NULL," +
				" apellidos VARCHAR(30)," +
				" especialidad VARCHAR(16)," +
				" PRIMARY KEY (dni)," +
				"  FOREIGN KEY (dni) REFERENCES personal(dni)," +
				"  FOREIGN KEY (cod_centro) REFERENCES centros (cod_centro));");
	//Relleno por primera vez
		rellenaCentros(db);
		rellenaPersonal(db);
		rellenaProfesores(db);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.e("mensaje", "entro en upgrade");
		
	}
	
	
	
	
	
	
	public void rellenaCentros(SQLiteDatabase db){
		db.execSQL("DELETE from centros");
		db.execSQL("INSERT INTO centros (cod_centro,tipo_centro,nombre,direccion, telefono,num_plazas)" +
				" select 10 as cod_centro,'S' as tipo_centro,'IES El Quijote' as nombre,'Avda. Los Molinos 25' as direccion, '965-887654' as telefono,538 as num_plazas " +
				"union select 15,'P','CP Los Danzantes', 'C/Las Musas s/n','985-112322',250 " +
				"union select 22,'S', 'IES Planeta Tierra', 'C/Mina 45','925-443400',300 " +
				"union select 45,'P', 'CP Manuel Hidalgo', 'C/Granada 5','926-202310',220 " +
				"union select 50,'S', 'IES Antoïnete 1', 'C/Los Toreros 21','989-406090',310;");
	}
	
	
	public void rellenaPersonal(SQLiteDatabase db){
		db.execSQL("DELETE from personal");
		db.execSQL("INSERT INTO personal (cod_centro,dni,apellidos,funcion, salario)"+
		"select 10 as cod_centro,4480099 as dni, 'Ruano Cerezo, Manuel' as apellidos,'ADMINISTRATIVO' as funcion, 1800.00 as salario "+
		"union select 15,1002345, 'Albarrïn Serrano, Alicia', 'ADMINISTRATIVO', 1800.00 "+
		"union select 15,7002660, 'Munyoz Rey, Felicia', 'ADMINISTRATIVO', 1800.00 "+
		"union select 22,5502678, 'Marïn Marn, Pedro', 'ADMINISTRATIVO', 1800.00 "+
		"union select 22,6600980, 'Peinado Gil, Elena','CONSERJE', 1750.00 "+
		"union select 45,4163222, 'Sarro Molina, Carmen','CONSERJE', 1750.00 "+
		"union select 10,1112345,'Martnez Salas, Fernando',  'PROFESOR',2200.00 "+
		"union select 10,4123005,'Bueno Zarco, Elisa', 'PROFESOR',2200.00 "+
		"union select 10,4122025,'Montes Garca, M.Pilar', 'PROFESOR',2200.00 "+
		"union select 15,9800990, 'Ramos Ruiz, Luis', 	'PROFESOR',2050.00 "+
		"union select 15,1112355,'Rivera Silvestre, Ana', 'PROFESOR',2050.00 "+
		"union select 15,8660990, 'De Lucas Fdez, M.Angel',  'PROFESOR',2050.00 "+
		"union select 22,7650000, 'Ruiz Lafuente, Manuel',  'PROFESOR',2200.00 "+
		"union select 45,43526789, 'Serrano Lagua, Marïa','PROFESOR',2050.00;");
	}
	
	public void rellenaProfesores (SQLiteDatabase db) {
		db.execSQL("DELETE from profesores");
		db.execSQL("INSERT INTO profesores (cod_centro,dni,apellidos,especialidad)" +
				"select 10 as cod_centro,1112345 as dni,'Martïnez Salas, Fernando' as apellidos,  'INFORMATICA' as especialidad "+
				"union select 10,4123005,'Bueno Zarco, Elisa', 'MATEMATICAS' "+
				"union select 10,4122025,'Montes Garcia, M.Pilar', 'MATEMATICAS' "+
				"union select 15,9800990, 'Ramos Ruiz, Luis', 	'LENGUA' "+
				"union select 15,1112355,'Rivera Silvestre, Ana', 'DIBUJO' "+
				"union select 15,8660990, 'De Lucas Fdez, M.Angel',  'MATEMATICAS' "+
				"union select 22,7650000, 'Ruiz Lafuente, Manuel',  'MATEMATICAS' "+
				"union select 45,43526789, 'Serrano Laguia, Maria','INFORMATICA' ");
	}

	public ArrayList<Integer> rellenaModeloPrimary(SQLiteDatabase db,String tabla,String []campo){
		ArrayList<Integer> modelo = new ArrayList<Integer>();
		Cursor c = db.query(tabla, campo,null,null,null,null,null);
		
		 if (c.moveToFirst()){
				do {
					int codigo = c.getInt(0);
					modelo.add(codigo);
				}while(c.moveToNext());
			
			}	
		return modelo;
	}
}
