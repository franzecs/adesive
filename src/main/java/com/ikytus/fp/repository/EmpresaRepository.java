package com.ikytus.fp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ikytus.fp.model.Empresa;

public interface EmpresaRepository extends PagingAndSortingRepository<Empresa, Long> {
	
	public Page<Empresa>findByNomeContainingIgnoreCase(String nome, Pageable pageable);
	
	public Page<Empresa>findByFornecedor(Empresa empresa, Pageable pageable);
	
	public List<Empresa>findByFornecedor(Empresa empresa);
	
	public Page<Empresa>findByNomeContainingAndFornecedor(String nome, Empresa empresa, Pageable pageable);

}
