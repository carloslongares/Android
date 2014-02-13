package com.example.centrospersonalprofesores;
import android.content.Context;

public class App {
	private static App app=null;
	SchemaCreator centroProDB;
	static Context context;
	public static void setcontext (Context contex){
		context=contex;
	}
	
	private App (){
		centroProDB = new SchemaCreator(context, "CentrosProfesores", null, 1);
	}
	
	public static App getInstance () {
		if (app == null)
			app = new App();
		return app;
	}
	
	public SchemaCreator getBaseDeDatos() {
		return centroProDB;
	}
}
