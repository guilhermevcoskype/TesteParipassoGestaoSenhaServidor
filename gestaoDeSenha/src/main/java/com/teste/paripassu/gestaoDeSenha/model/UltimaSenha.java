package com.teste.paripassu.gestaoDeSenha.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UltimaSenha {
	
	@Id
	private Long id;
		
	private String senhaNormal;
	
	private String senhaPreferencial;
	
	public UltimaSenha() {};
	
	public UltimaSenha(Long id, String senhaNormal, String senhaPreferencial) {
		this.id = id;
		this.senhaNormal = senhaNormal;
		this.senhaPreferencial = senhaPreferencial;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSenhaNormal() {
		return senhaNormal;
	}

	public void setSenhaNormal(String senhaNormal) {
		this.senhaNormal = senhaNormal;
	}

	public String getSenhaPreferencial() {
		return senhaPreferencial;
	}

	public void setSenhaPreferencial(String senhaPreferencial) {
		this.senhaPreferencial = senhaPreferencial;
	}
	
}
