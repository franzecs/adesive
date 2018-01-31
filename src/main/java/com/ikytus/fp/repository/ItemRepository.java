package com.ikytus.fp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ikytus.fp.model.Item;
import com.ikytus.fp.model.Requisicao;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {
	
	public Page<Item>findByRequisicao(Requisicao requisicao, Pageable pageable);
}
