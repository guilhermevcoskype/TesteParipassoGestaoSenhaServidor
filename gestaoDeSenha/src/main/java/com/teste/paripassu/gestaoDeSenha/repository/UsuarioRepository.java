package com.teste.paripassu.gestaoDeSenha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.paripassu.gestaoDeSenha.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{

}
