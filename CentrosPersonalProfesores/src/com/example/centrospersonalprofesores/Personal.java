package com.example.centrospersonalprofesores;

public class Personal {
	
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
	public String getFuncion() {
		return funcion;
	}
	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}
	public int getSalario() {
		return salario;
	}
	public void setSalario(int salario) {
		this.salario = salario;
	}
	private int codCentro;
	private int dni;
	private String apellidos;
	private String funcion;
	private int salario;
	public Personal (int codCentro, int dni, String apellidos, String funcion,int salario){
		this.codCentro = codCentro;
		this.dni= dni;
		this.apellidos= apellidos;
		this.funcion=funcion;
		this.salario= salario;
	}
}
