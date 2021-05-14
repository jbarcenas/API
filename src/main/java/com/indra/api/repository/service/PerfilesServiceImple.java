package com.indra.api.repository.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indra.api.model.Perfiles;
import com.indra.api.repository.PerfilesRepository;


@Service
public class PerfilesServiceImple implements PerfilesService{

	@Autowired
	private PerfilesRepository repo;
	
	public List<Perfiles> buscar() {
		
		return (List<Perfiles>) repo.findAll();
	}

}
