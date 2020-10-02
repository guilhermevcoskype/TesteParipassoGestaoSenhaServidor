package com.teste.paripassu.gestaoDeSenha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.paripassu.gestaoDeSenha.model.UltimaSenha;
import com.teste.paripassu.gestaoDeSenha.repository.UltimaSenhaRepository;

@Service
public class UltimaSenhaService {

	@Autowired
	private UltimaSenhaRepository ultimaSenhaRepository;

	public UltimaSenha buscarUltimaSenha() {

		return ultimaSenhaRepository.findById((long) 1).orElse(null);
	}

	public void salvarUltimaSenha(UltimaSenha ultimaSenha) {

		ultimaSenhaRepository.save(ultimaSenha);
	}
}
