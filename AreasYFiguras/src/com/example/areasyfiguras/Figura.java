package com.example.areasyfiguras;

public class Figura {

	private String tipo;
	private int foto ;
	
	public Figura (String tipo, int foto) {
		this.tipo=tipo;
		this.foto=foto;
	}
	
	
	public String getTipo () {
		return tipo;
	}
	
	
	
	public int getFoto(){
		return foto;
	}
}
