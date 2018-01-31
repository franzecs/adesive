package com.ikytus.fp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ikytus.fp.model.Empresa;
import com.ikytus.fp.model.Requisicao;

public interface RequisicaoRepository extends PagingAndSortingRepository<Requisicao, Long> {
	
	public Page<Requisicao>findByStatus(String status, Pageable pageable);
	
	public Page<Requisicao>findByStatusAndEmpresa(String status, Empresa empresa, Pageable pageable);

}
