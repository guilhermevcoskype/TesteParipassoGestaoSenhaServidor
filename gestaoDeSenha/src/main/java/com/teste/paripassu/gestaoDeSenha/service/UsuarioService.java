package com.teste.paripassu.gestaoDeSenha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.teste.paripassu.gestaoDeSenha.model.Usuario;
import com.teste.paripassu.gestaoDeSenha.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return repository.findById(email).orElse(null);
	}

	public void salvar(Usuario usuario) {
		repository.save(usuario);
	}
}
