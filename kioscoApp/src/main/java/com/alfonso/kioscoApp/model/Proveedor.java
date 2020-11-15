package com.alfonso.kioscoApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //anotacion que identifica la clase como entidad
@Table(name="proveedor") //nombre de la tabla en bd
public class Proveedor {

	@Id //Anotacion que indica que el atributo es la clave primaria
	@GeneratedValue(strategy=GenerationType.IDENTITY)//Auto_increment MYSQL
	private int id;
	private String nombre;
	private String direccion;
	private String telefono1;;
	private String telefono2;
	private String email;
	private String estatus;
	
	
	public Proveedor() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public String getTelefono1() {
		return telefono1;
	}


	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}


	public String getTelefono2() {
		return telefono2;
	}


	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getEstatus() {
		return estatus;
	}


	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}


	@Override
	public String toString() {
		return "Proveedor [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono1=" + telefono1
				+ ", telefono2=" + telefono2 + ", email=" + email + ", estatus=" + estatus + "]";
	}
	
}
