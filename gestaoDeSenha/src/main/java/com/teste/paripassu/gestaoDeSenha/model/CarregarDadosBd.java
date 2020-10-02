package com.teste.paripassu.gestaoDeSenha.model;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.teste.paripassu.gestaoDeSenha.repository.RoleRepository;
import com.teste.paripassu.gestaoDeSenha.repository.UsuarioRepository;

@Component
public class CarregarDadosBd implements ApplicationListener<ContextRefreshedEvent> {	
	 
	    boolean jaConfigurado = false;
	 
	    @Autowired
	    private UsuarioRepository usuarioRepository;
	 
	    @Autowired
	    private RoleRepository roleRepository;
	 
	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    
	    @Override
	    @Transactional
	    public void onApplicationEvent(ContextRefreshedEvent event) {
	 
	        if (jaConfigurado)
	            return;
	 
	        createRoleIfNotFound("ROLE_ADMIN");
	        createRoleIfNotFound("ROLE_USER");
	 
	        Role adminRole = roleRepository.findById("ROLE_ADMIN").get();
	        Usuario usuario = new Usuario();
	        usuario.setNome("Gerente");
	        usuario.setSenha(passwordEncoder.encode("123"));
	        usuario.setEmail("gerente@gerente.com");
	        usuario.setRoles(Arrays.asList(adminRole));
	        usuarioRepository.save(usuario);
	 
	        jaConfigurado = true;
	    }
	 
	    @Transactional
	    Role createRoleIfNotFound(String nome) {
	    	Optional<Role> optRole = roleRepository.findById(nome);
	    	Role role = null;
	        if (!optRole.isPresent()) {
	            role = new Role();
	            role.setNome(nome);
	            roleRepository.save(role);
	        }else role = optRole.get();
	        
	        return role;
	    }
}
