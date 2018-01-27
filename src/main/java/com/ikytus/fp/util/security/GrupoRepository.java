package com.ikytus.fp.util.security;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.fp.util.security.model.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {
	
	public Grupo findByNome(String nome);
	
	public List<Grupo> findByNomeNot(String nome);
}
