package com.alfonso.kioscoApp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="banners")
public class Banner {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//Auto_increment MYSQL
	private int id;
	private String titulo;
	private Date fecha; // Fecha de Publicacion del Banner
	private String archivo; // atributo para guardar el nombre de la imagen
	private String estatus;
	private String detalle;
//	@OneToOne
//	@JoinColumn(name="idPelicula") //une una columna de tabla pelicula a tabla horario 
//	Pelicula pelicula;
	
	
	public Banner() {
		this.fecha= new Date();
		this.estatus= "Activo";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public String getEstatus() {
		return estatus;
	}

	@Override
	public String toString() {
		return "Banner [id=" + id + ", titulo=" + titulo + ", fecha=" + fecha + ", archivo=" + archivo + ", estatus="
				+ estatus + ", detalle=" + detalle + "]";
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

		
	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
}
