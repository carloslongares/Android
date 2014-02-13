package com.example.centrospersonalprofesores;

public class Profesores {
	
	public int getCodCentro() {
		return codCentro;
	}
	public void setCodCentro(int codCentro) {
		this.codCentro = codCentro;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	private int codCentro;
	private int dni;
	private String apellidos;
	private String especialidad;
	
	public Profesores (int codCentro, int dni, String apellidos, String especialidad) {
		this.codCentro = codCentro;
		this.dni = dni;
		this.apellidos = apellidos;
		this.especialidad = especialidad;
	}
	
}
