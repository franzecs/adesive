package com.ikytus.fp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ikytus.fp.model.Produto;
import com.ikytus.fp.repository.ProdutoRepository;

@Service
public class ProdutoService implements AbstractService<Produto>{
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public void salvar(Produto produto){
		this.produtoRepository.save(produto);
	}
		
	public void salvar(Produto produto, String imagem){
		produto.setImg(imagem);
		this.produtoRepository.save(produto);
	}
	
	public void deletar(Produto produto) {
		produtoRepository.delete(produto);
	}

}
