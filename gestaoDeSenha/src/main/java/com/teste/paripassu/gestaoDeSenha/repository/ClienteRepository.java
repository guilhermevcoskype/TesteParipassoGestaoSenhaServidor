package com.teste.paripassu.gestaoDeSenha.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.paripassu.gestaoDeSenha.model.Cliente;
import com.teste.paripassu.gestaoDeSenha.model.TipoCliente;

public interface ClienteRepository extends JpaRepository<Cliente, String>{

	public Optional<Cliente> findTopByTipoClienteOrderByIdDesc(TipoCliente tipoCliente);
	
	public Optional<Cliente> findTopByTipoClienteOrderById(TipoCliente tipoCliente);

}
