package com.ikytus.fp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ikytus.fp.model.Empresa;
import com.ikytus.fp.model.Requisicao;
import com.ikytus.fp.model.ENUM.Status;

public interface RequisicaoRepository extends PagingAndSortingRepository<Requisicao, Long> {
	
	public Page<Requisicao>findByStatus(String status, Pageable pageable);
	
	public Page<Requisicao>findByEmpresaAndStatus(Empresa empresa, Status status, Pageable pageable);
	
	public Page<Requisicao>findByEmpresa(Empresa empresa, Pageable pageable);

}
