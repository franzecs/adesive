package com.ikytus.fp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ikytus.fp.model.Empresa;
import com.ikytus.fp.model.Produto;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long> {
	
	public Page<Produto>findByNomeContainingIgnoreCase(String nome, Pageable pageable);
	
	public Page<Produto>findByNomeContainingAndEmpresa(String nome, Empresa empresa, Pageable pageable);

}
