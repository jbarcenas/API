package com.indra.api.repository.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indra.api.controller.HomeController;
import com.indra.api.model.Usuarios;
import com.indra.api.model.UsuariosResponse;
import com.indra.api.repository.UsuariosRepository;


@Service
public class UsuariosServiceImple implements UsuariosService {
	private final static Logger Log =  LoggerFactory.getLogger(UsuariosServiceImple.class);
	
	@Autowired
	private UsuariosRepository repo;
	
	public List<Usuarios> buscar() {
		
		return (List<Usuarios>) repo.findAll();
	}

	
	public UsuariosResponse buscarUsuario(Usuarios usuarioObje) {
		Optional<Usuarios> encuentra = repo.findById(usuarioObje.getUsuario());
		UsuariosResponse userResponse = new UsuariosResponse();
		Usuarios user = new Usuarios();
		if(encuentra.isPresent()) {
			
			user =encuentra.get();
			if(user.getClave().equals(usuarioObje.getClave())) {
				userResponse.setStatus("ok");
				userResponse.setNombre(user.getNombre());
				userResponse.setPerfil(user.getPerfil().getPerfil());
				userResponse.setId(user.getPerfil().getIdperfil());
				
			}
			else {
				userResponse.setStatus("error");
				userResponse.setMensaje("Password incorrecta");
			}
			
		}else {
			userResponse.setStatus("error");
			userResponse.setMensaje("Usuario incorrecto");
		}
		return  userResponse;
	}



	public String guardar(Usuarios user) {
		Usuarios usuario = new Usuarios();
	 try {
		 Log.info("UsuarioSevice {}",user);
		 repo.save(user);
		 return "Registro guardado";
	 }catch(Exception e) {
		 return "Error, no se guardo registro";
	 }
		
	}
	

}
