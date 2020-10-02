package com.teste.paripassu.gestaoDeSenha.model;

import com.teste.paripassu.gestaoDeSenha.service.ClienteService;
import com.teste.paripassu.gestaoDeSenha.service.UltimaSenhaService;

public class RetornaSenha {

	private ClienteService clienteService;
	
	private UltimaSenhaService ultimaSenhaService;
	
	private UltimaSenha ultimaSenha;
	
	
	public RetornaSenha(ClienteService clienteService, UltimaSenhaService ultimaSenhaService) {
		this.clienteService = clienteService;
		ultimaSenha = ultimaSenhaService.buscarUltimaSenha();
		if(ultimaSenha==null) {
			ultimaSenha = new UltimaSenha(new Long(1), "0", "0");
		}
		this.ultimaSenhaService = ultimaSenhaService;
	}

	public String retornarProximaSenha() {
		Cliente cliente = clienteService.buscarProximoClienteNoBd();
		if (cliente == null)
			return "Não há mais senhas";
		else {
			if(cliente.getTipoCliente().equals(TipoCliente.NORMAL)) {
				ultimaSenha.setSenhaNormal(cliente.getSenha());
				ultimaSenhaService.salvarUltimaSenha(ultimaSenha);
			}else {
				ultimaSenha.setSenhaPreferencial(cliente.getSenha());
				ultimaSenhaService.salvarUltimaSenha(ultimaSenha);
			}
			clienteService.deletarCliente(cliente);
			return cliente.getSenha();
		}
	}

	public String gerarSenha(TipoCliente tipoCliente) {
		Cliente cliente = clienteService.buscarUltimoClienteNoBdPorTipo(tipoCliente);
		if (cliente == null) {
			if(tipoCliente.getTipo().equals(TipoCliente.NORMAL.getTipo())) {
				if(ultimaSenha.getSenhaNormal().equals("0")) {
					ultimaSenha.setSenhaNormal("N" + transformarIdParaSenha(new Long(ultimaSenha.getSenhaNormal())+1));
					ultimaSenhaService.salvarUltimaSenha(ultimaSenha);
				}
				return ultimaSenha.getSenhaNormal();
			}else {
				if(ultimaSenha.getSenhaPreferencial().equals("0")) {
					ultimaSenha.setSenhaPreferencial("P" + transformarIdParaSenha(new Long(ultimaSenha.getSenhaPreferencial())+1));
					ultimaSenhaService.salvarUltimaSenha(ultimaSenha);
				}
				 return ultimaSenha.getSenhaPreferencial();
			}
		}
		if (cliente.getTipoCliente().equals(TipoCliente.PREFERENCIAL)) {
			ultimaSenha.setSenhaPreferencial(cliente.getSenha());
			ultimaSenhaService.salvarUltimaSenha(ultimaSenha);
			return "P" + transformarIdParaSenha(new Long(cliente.getSenha().substring(1))+1);
		}else {
			ultimaSenha.setSenhaNormal(cliente.getSenha());
			ultimaSenhaService.salvarUltimaSenha(ultimaSenha);
			return "N" + transformarIdParaSenha(new Long(cliente.getSenha().substring(1))+1);
		}
	}
	
	public String transformarIdParaSenha(Long num) {
		String numString = num.toString();
		int tamanhoInicial = numString.length();
		for (int i = 3; i >= tamanhoInicial; i--) {
			numString = "0" + numString;
		}
		return numString;
	}
}
