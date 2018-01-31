package com.ikytus.fp.util.security;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.fp.util.security.model.Grupo;
import com.ikytus.fp.util.security.model.Usuario;

@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {
	
	public Usuario findByFuncional(String funcional);
	
	public Page<Usuario> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
	
	public Page<Usuario> findByNomeContainingIgnoreCaseAndGruposNotContaining(String nome,Grupo grupo,Pageable pageable);

}
