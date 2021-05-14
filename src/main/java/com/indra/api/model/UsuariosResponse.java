package com.indra.api.model;

public class UsuariosResponse {
	private String status;
	private String mensaje;
	private String nombre;
	private int id;
	private String perfil;
	
	
	
   public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getPerfil() {
		return perfil;
	}



	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}



public UsuariosResponse() {
		
	}
   
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	
}
