package com.goodtrip.tcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goodtrip.tcc.models.Passagem;

public interface PassagemRepository extends JpaRepository<Passagem, Long> {

}
