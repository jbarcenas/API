package com.indra.api.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="USUARIOS")
public class Usuarios {
	
	
	@Id
	private String usuario;
	
	private String clave;
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name="IDPERFIL",nullable=false)
	private Perfiles perfil;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Perfiles getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfiles perfil) {
		this.perfil = perfil;
	}
	
	
	
	
	public String toString() {
		return "usuario=" + usuario + ", clave=" + clave + ", nombre=" + nombre + ", perfil=" + perfil
				+ ", getUsuario()=" + getUsuario() + ", getClave()=" + getClave() + ", getNombre()=" + getNombre()
				+ ", getPerfil()=" + getPerfil() ;
	}
	
}
