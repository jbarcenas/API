package com.indra.api.repository.service;

import java.util.List;

import com.indra.api.model.Usuarios;
import com.indra.api.model.UsuariosResponse;


public interface UsuariosService {
	List<Usuarios> buscar();
	UsuariosResponse buscarUsuario(Usuarios user);
	String  guardar(Usuarios user);
}
