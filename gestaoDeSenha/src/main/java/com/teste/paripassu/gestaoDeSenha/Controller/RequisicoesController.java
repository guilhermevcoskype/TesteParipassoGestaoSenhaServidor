package com.teste.paripassu.gestaoDeSenha.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teste.paripassu.gestaoDeSenha.model.Cliente;
import com.teste.paripassu.gestaoDeSenha.model.RetornaSenha;
import com.teste.paripassu.gestaoDeSenha.model.TipoCliente;
import com.teste.paripassu.gestaoDeSenha.service.ClienteService;
import com.teste.paripassu.gestaoDeSenha.service.ReiniciarSequenciaCliente;
import com.teste.paripassu.gestaoDeSenha.service.UltimaSenhaService;

@RestController
public class RequisicoesController {

	@Autowired
	ClienteService clienteService;
	
	@Autowired
	UltimaSenhaService ultimaSenhaService;

	Cliente cliente;
	
	@Autowired
	ReiniciarSequenciaCliente reiniciar;
	
	public String senhaGerada;
	public String proximaSenha;

	@RequestMapping(value = "/receberRequisicao", method = RequestMethod.GET)
	public ResponseEntity<String> proximaSenha() {
		proximaSenha = new RetornaSenha(clienteService, ultimaSenhaService).retornarProximaSenha();
		return ResponseEntity.ok(proximaSenha);
	}

	@RequestMapping(value = "/receberRequisicao", method = RequestMethod.POST)
	public ResponseEntity<String> requisicaoPost(@RequestBody String requisicao) {
		if (!requisicao.equals("zerarsenhas")) {
			cliente = new Cliente();
			if (requisicao.equals(TipoCliente.NORMAL.getTipo())) {
				cliente.setTipoCliente(TipoCliente.NORMAL);
			} else if (requisicao.equals(TipoCliente.PREFERENCIAL.getTipo())) {
				cliente.setTipoCliente(TipoCliente.PREFERENCIAL);
			} else
				return ResponseEntity.badRequest().body("Argumento não aceito");
			senhaGerada = new RetornaSenha(clienteService, ultimaSenhaService).gerarSenha(cliente.getTipoCliente());
			cliente.setSenha(senhaGerada);
			clienteService.salvarCliente(cliente);
			return ResponseEntity.ok(senhaGerada);
		} else {
			reiniciar.reiniciarSequenciaCliente();
			return ResponseEntity.ok("Senhas Reiniciadas");
		}
	}
}
