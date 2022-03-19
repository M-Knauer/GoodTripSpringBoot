package com.goodtrip.tcc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.goodtrip.tcc.models.Cliente;
import com.goodtrip.tcc.models.Passagem;
import com.goodtrip.tcc.models.Telefone;
import com.goodtrip.tcc.repositories.ClienteRepository;
import com.goodtrip.tcc.repositories.PassagemRepository;
import com.goodtrip.tcc.repositories.TelefoneRepository;

@Configuration
@Profile("test")
public class TestConfig implements ApplicationRunner {
	
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private TelefoneRepository tel;
    
    @Autowired
    private PassagemRepository pass;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
    	
        Cliente u1 = new Cliente("Maria Brown", "maria@gmail.com", "988888888");
        Cliente u2 = new Cliente("Alex Green", "alex@gmail.com", "977777777");
      
        
        Telefone tel1 = new Telefone("1223446", "54545666", u1);
        Telefone tel2 = new Telefone("4455669", "77871112", u2);
        
        
        Passagem p1 = new Passagem("RJ", "SP", 1222.50, u1);
        Passagem p2 = new Passagem("MG", "RS", 1110.50, u2);
        Passagem p3 = new Passagem("ES", "BA", 1555.5, u1);
		
        
        clienteRepository.saveAll(Arrays.asList(u1, u2));
        tel.saveAll(Arrays.asList(tel1, tel2));
        pass.saveAll(Arrays.asList(p1, p2, p3));
    }
}
