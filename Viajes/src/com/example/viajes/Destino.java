package com.example.viajes;

public class Destino {

	private String zona;
	private String continente;
	private int precio;
	private int foto;
	
	public Destino(String zona, String continente, int precio,int foto) {
		this.zona=zona;
		this.continente= continente;
		this.precio=precio;
		this.foto=foto;
	}
	
	public int getFoto(){
		return foto;
	}
	
	public String getZona(){
		return zona;
	}
	
	public String getContinente () {
		return continente;
		
	}
	
	public int getPrecio (){
		return precio;
	}
	
	
	public void setZona (String nuevaZona){
		zona = nuevaZona;
	}
	
	public void setContinente (String nuevoContinente){
		continente= nuevoContinente;
	}
	
	public void setPrecio (int nuevoPrecio){
		precio= nuevoPrecio;
	}
	
}
