package com.goodtrip.tcc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goodtrip.tcc.models.Cliente;
import com.goodtrip.tcc.repositories.ClienteRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> findAll(){
		List<Cliente> clientes = clienteRepository.findAll();
		if (!clientes.isEmpty()) {
			return new ResponseEntity<>(clientes, HttpStatus.OK);
		}
		return new ResponseEntity<List<Cliente>>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Long id){
		Optional<Cliente> clienteData = clienteRepository.findById(id);
		if (clienteData.isPresent()) {
			return new ResponseEntity<Cliente>(clienteData.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
	}
	
	 @PostMapping("/registrar")
	  public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
	    try {
	      Cliente c = clienteRepository.save(cliente);
	      return new ResponseEntity<>(c, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 
	 @PutMapping("/{id}")
	 public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente){
		 Optional<Cliente> clienteData = clienteRepository.findById(id);
		 if (clienteData.isPresent()) {
			 Cliente c = clienteData.get();
			 c.setNome(cliente.getNome());
			 c.setSobrenome(cliente.getSobrenome());
			 return new ResponseEntity<Cliente>(clienteRepository.save(c), HttpStatus.OK);
		 }
		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	 }
	 
}
