package com.ikytus.fp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ikytus.fp.model.Item;
import com.ikytus.fp.model.Requisicao;
import com.ikytus.fp.repository.ItemRepository;

@Service
public class ItemService implements AbstractService<Item>{
	
	@Autowired
	private ItemRepository itemRepository;
	
	public void salvar(Item item){
		this.itemRepository.save(item);
	}
		
	public void salvar(Item item, String imagem){
		item.setImg(imagem);
		this.itemRepository.save(item);
	}
	
	public void deletar(Long codigo) {
		itemRepository.delete(codigo);
	}

	public void salvar(Item item, Requisicao requisicao) {
		item.setRequisicao(requisicao);
		this.itemRepository.save(item);
	}

}
