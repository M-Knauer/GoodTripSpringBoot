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

import com.goodtrip.tcc.models.Telefone;
import com.goodtrip.tcc.repositories.TelefoneRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/telefones")
public class TelefoneController {
	
	@Autowired
	TelefoneRepository telefoneRepository;
	
	@GetMapping
	public ResponseEntity<List<Telefone>> findAll(){
		List<Telefone> telefones = telefoneRepository.findAll();
		if (!telefones.isEmpty()) {
			return new ResponseEntity<>(telefones, HttpStatus.OK);
		}
		return new ResponseEntity<List<Telefone>>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Telefone> findById(@PathVariable Long id){
		Optional<Telefone> telefoneData = telefoneRepository.findById(id);
		if (telefoneData.isPresent()) {
			return new ResponseEntity<Telefone>(telefoneData.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Telefone>(HttpStatus.NOT_FOUND);
	}
	
	 @PostMapping("/registrar")
	  public ResponseEntity<Telefone> createTelefone(@RequestBody Telefone telefone) {
	    try {
	      Telefone t = telefoneRepository.save(telefone);
	      return new ResponseEntity<>(t, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 
	 @PutMapping("/{id}")
	 public ResponseEntity<Telefone> updateTelefone(@PathVariable Long id, @RequestBody Telefone telefone){
		 Optional<Telefone> telefoneData = telefoneRepository.findById(id);
		 if (telefoneData.isPresent()) {
			 Telefone t = telefoneData.get();
			 t.setCelular(telefone.getCelular());
			 t.setFixo(telefone.getFixo());
			 return new ResponseEntity<Telefone>(telefoneRepository.save(t), HttpStatus.OK);
		 }
		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	 }
	 
	 
}
