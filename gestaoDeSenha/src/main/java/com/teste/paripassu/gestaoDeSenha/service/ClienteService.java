package com.teste.paripassu.gestaoDeSenha.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.paripassu.gestaoDeSenha.model.Cliente;
import com.teste.paripassu.gestaoDeSenha.model.TipoCliente;
import com.teste.paripassu.gestaoDeSenha.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscarUltimoClienteNoBd() {
		Cliente cliente = buscarUltimoClienteNoBdPorTipo(TipoCliente.PREFERENCIAL);
		if (cliente!=null) {
			return cliente;
		} else {
			cliente = buscarUltimoClienteNoBdPorTipo(TipoCliente.NORMAL);
			if (cliente!=null) {
				return cliente;
			} else {
				return null;
			}
		}
	}

	public Cliente buscarUltimoClienteNoBdPorTipo(TipoCliente tipoCliente) {
		Optional<Cliente> clienteOptional = clienteRepository.findTopByTipoClienteOrderByIdDesc(tipoCliente);
		if (clienteOptional.isPresent()) {
			return clienteOptional.get();
		} else {
			return null;
		}
	}
	
	public Cliente buscarPrimeiroClienteNoBdPorTipo(TipoCliente tipoCliente) {
		Optional<Cliente> clienteOptional = clienteRepository.findTopByTipoClienteOrderById(tipoCliente);
		if (clienteOptional.isPresent()) {
			return clienteOptional.get();
		} else {
			return null;
		}
	}
	
	public Cliente buscarProximoClienteNoBd() {
		Cliente cliente = buscarPrimeiroClienteNoBdPorTipo(TipoCliente.PREFERENCIAL);
		if (cliente!=null) {
			return cliente;
		} else {
			cliente = buscarPrimeiroClienteNoBdPorTipo(TipoCliente.NORMAL);
			if (cliente!=null) {
				return cliente;
			} else {
				return null;
			}
		}
	}
	
	public void salvarCliente(Cliente cliente) {
		clienteRepository.save(cliente);
	}
	
	public void deletarCliente(Cliente cliente) {
		clienteRepository.delete(cliente);
	}
	
}
