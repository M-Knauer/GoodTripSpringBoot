package com.goodtrip.tcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goodtrip.tcc.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
