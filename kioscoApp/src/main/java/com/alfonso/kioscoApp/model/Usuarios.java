package com.alfonso.kioscoApp.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="usuarios")
public class Usuarios {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id ;
	@NotEmpty
	private String cuenta;
	private String pwd;
	private int activo;
	@NotEmpty
	@Email
	private String email;
	private String telefono;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name="usuarioperfil",
			joinColumns = @JoinColumn( name="idusuario"),
			inverseJoinColumns = @JoinColumn(name="idperfil")
	)
	List<Perfil> perfiles;
	
	public void agregar(Perfil tempPerfil) {
		if(perfiles == null) {
			perfiles = new LinkedList<Perfil>();
		}
		perfiles.add(tempPerfil);		
	}
	
	
	public List<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	public Usuarios() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getActivo() {
		return activo;
	}
	public void setActivo(int activo) {
		this.activo = activo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	@Override
	public String toString() {
		return "Usuarios [id=" + id + ", cuenta=" + cuenta + ", pwd=" + pwd + ", activo=" + activo + ", email=" + email
				+ ", telefono=" + telefono + ", perfiles=" + perfiles + "]";
	}	
	
}
