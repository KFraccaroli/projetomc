package com.fraccaroli.springtab.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fraccaroli.springtab.domain.Categoria;
import com.fraccaroli.springtab.domain.Cliente;
import com.fraccaroli.springtab.repositories.ClienteRepository;
import com.fraccaroli.springtab.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id){
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrato! id: " +  id + ", Tipo: " + Cliente.class.getName()));
	}
}
