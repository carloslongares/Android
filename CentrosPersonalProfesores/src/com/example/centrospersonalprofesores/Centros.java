package com.example.centrospersonalprofesores;

public class Centros {
	
	
	public Centros(int codCentro, String tipoCentro, String nombre,
			String direccion, String telefono, int numPlazas) {
		super();
		this.codCentro = codCentro;
		this.tipoCentro = tipoCentro;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.numPlazas = numPlazas;
	}
	
	public int getCodCentro() {
		return codCentro;
	}
	public void setCodCentro(int codCentro) {
		this.codCentro = codCentro;
	}
	public String getTipoCentro() {
		return tipoCentro;
	}
	public void setTipoCentro(String tipoCentro) {
		this.tipoCentro = tipoCentro;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public int getNumPlazas() {
		return numPlazas;
	}
	public void setNumPlazas(int numPlazas) {
		this.numPlazas = numPlazas;
	}
	private int codCentro;
	private String tipoCentro;
	private String nombre;
	private String direccion;
	private String telefono;
	private int numPlazas;
}
