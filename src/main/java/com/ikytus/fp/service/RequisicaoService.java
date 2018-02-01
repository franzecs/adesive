package com.ikytus.fp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ikytus.fp.model.Requisicao;
import com.ikytus.fp.model.ENUM.Status;
import com.ikytus.fp.repository.RequisicaoRepository;

@Service
public class RequisicaoService implements AbstractService<Requisicao>{
	
	@Autowired
	private RequisicaoRepository requisicaoRepository;
	
	public void salvar(Requisicao requisicao){
		this.requisicaoRepository.save(requisicao);
	}
		
	public void salvar(Requisicao requisicao, String imagem){
		requisicao.setImg(imagem);
		this.requisicaoRepository.save(requisicao);
	}
	
	public void deletar(Long codigo) {
		requisicaoRepository.delete(codigo);
	}
	
	public String entregar(Long codigo) {
		Requisicao requisicao= requisicaoRepository.findOne(codigo);
		requisicao.setStatus(Status.ENTREGUE);
		requisicaoRepository.save(requisicao);
		
		return Status.ENTREGUE.getDescricao();
	}

}
