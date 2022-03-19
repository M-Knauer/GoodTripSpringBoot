package com.goodtrip.tcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goodtrip.tcc.models.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

}
