package com.teste.paripassu.gestaoDeSenha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReiniciarSequenciaCliente {

	@Autowired
	private CriadordeQueryNativa criadordeQueryNativa;
	
	public void reiniciarSequenciaCliente(){
		criadordeQueryNativa.execute("alter sequence CLI_SEQ restart; TRUNCATE table Cliente;");
    }
	
}
