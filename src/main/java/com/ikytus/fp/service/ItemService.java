package com.ikytus.fp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikytus.fp.model.Item;
import com.ikytus.fp.model.Requisicao;
import com.ikytus.fp.repository.ItemRepository;
import com.ikytus.fp.repository.ProdutoRepository;
import com.ikytus.fp.repository.RequisicaoRepository;

@Service
public class ItemService implements AbstractService<Item>{
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private RequisicaoRepository requisicaoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public void salvar(Item item){
		this.itemRepository.save(item);
	}
		
	public void salvar(Item item, String imagem){
		item.setImg(imagem);
		itemRepository.save(item);
	}
	
	public void salvar(String codigoRequisicao, Item item){
		if(item.getValorunitario()==null) {
			item.setValorunitario((produtoRepository.findOne(item.getProduto().getCodigo())).getValor());
		}
		item.setRequisicao(requisicaoRepository.findByCodigo((long) Integer.parseInt(codigoRequisicao)));
		
		itemRepository.save(item);
	}
	
	public void deletar(Long codigo) {
		itemRepository.delete(codigo);
	}

	public void salvar(Item item, Requisicao requisicao) {
		item.setRequisicao(requisicao);
		this.itemRepository.save(item);
	}

}
