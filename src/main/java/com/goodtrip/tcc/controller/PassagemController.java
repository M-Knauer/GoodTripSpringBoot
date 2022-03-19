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

import com.goodtrip.tcc.models.Passagem;
import com.goodtrip.tcc.repositories.PassagemRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/passagens")
public class PassagemController {
	
	@Autowired
	PassagemRepository PassagemRepository;
	
	@GetMapping
	public ResponseEntity<List<Passagem>> findAll(){
		List<Passagem> passagens = PassagemRepository.findAll();
		if (!passagens.isEmpty()) {
			return new ResponseEntity<>(passagens, HttpStatus.OK);
		}
		return new ResponseEntity<List<Passagem>>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Passagem> findById(@PathVariable Long id){
		Optional<Passagem> passagemData = PassagemRepository.findById(id);
		if (passagemData.isPresent()) {
			return new ResponseEntity<Passagem>(passagemData.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Passagem>(HttpStatus.NOT_FOUND);
	}
	
	 @PostMapping("/registrar")
	  public ResponseEntity<Passagem> createPassagem(@RequestBody Passagem passagem) {
	    try {
	      Passagem p = PassagemRepository.save(passagem);
	      return new ResponseEntity<>(p, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 
	 @PutMapping("/{id}")
	 public ResponseEntity<Passagem> updatePassagem(@PathVariable Long id, @RequestBody Passagem passagem){
		 Optional<Passagem> passagemData = PassagemRepository.findById(id);
		 if (passagemData.isPresent()) {
			 Passagem p = passagemData.get();
			 p.setEmbarque(passagem.getEmbarque());
			 p.setDesembarque(passagem.getDesembarque());
			 return new ResponseEntity<Passagem>(PassagemRepository.save(p), HttpStatus.OK);
		 }
		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	 }
	 
	 
}
