package com.teste.paripassu.gestaoDeSenha.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class CriadordeQueryNativa {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void execute(String sql){
        entityManager.createNativeQuery(sql).executeUpdate();
    }

}
