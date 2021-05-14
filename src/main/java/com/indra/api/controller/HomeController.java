package com.indra.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.indra.api.model.Perfiles;
import com.indra.api.model.Usuarios;
import com.indra.api.model.UsuariosResponse;
import com.indra.api.repository.service.PerfilesService;
import com.indra.api.repository.service.UsuariosService;


@RestController
@CrossOrigin(origins = "*", methods={RequestMethod.GET,RequestMethod.POST})
@RequestMapping(value="/")
public class HomeController {
	private final static Logger Log =  LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private PerfilesService serviceP;
	
	@Autowired
	private UsuariosService serviceU;
	
	
	
	@GetMapping("perfiles")
	public ResponseEntity <List<Perfiles>> getPerfil(){
		
		List<Perfiles> perfiles =serviceP.buscar();
		return ResponseEntity.ok(perfiles);
	}
	
	
	/*
	@PostMapping("usuarios")
	public ResponseEntity<UsuariosResponse> getUsuarios(@RequestBody Usuarios user){
	
		UsuariosResponse usuarioResponse = serviceU.buscarUsuario(user);
		
		return ResponseEntity.ok(usuarioResponse);
	}*/
	@PostMapping("usuarios")
	public String  getUsuarios(@RequestBody Usuarios user) throws JsonProcessingException{
		
		UsuariosResponse usuarioResponse = serviceU.buscarUsuario(user);
		ObjectMapper obcjetcMapper = new ObjectMapper();
		String json = obcjetcMapper.writeValueAsString(usuarioResponse);
		if("error".equals(usuarioResponse.getStatus())) {
			json="{\"mensaje\" : "+"\""+usuarioResponse.getMensaje()+"\"}";
		}
		
		return json;
	}
	
	
	@CrossOrigin(origins = "*", methods={RequestMethod.POST})
	@PostMapping("/usuarios/guardar")
	public ResponseEntity<java.lang.String> postUsuarios(@RequestBody Usuarios user){
		Perfiles perfiles = new Perfiles();
		perfiles.setIdperfil(3);
		user.setPerfil(perfiles);
		Log.info("Usuario {}",user.toString());
		return ResponseEntity.ok(serviceU.guardar(user));
	}

}
