package com.teste.paripassu.gestaoDeSenha.model;

public enum TipoCliente {
	NORMAL("normal"), PREFERENCIAL("preferencial");

	private final String tipo;

	TipoCliente(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
}
