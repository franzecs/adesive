package com.ikytus.fp.service;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikytus.fp.model.Empresa;
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
	
	public Requisicao novaRequisicao(Requisicao requisicao, Empresa empresa ) {
		requisicao.setData(Date.valueOf(LocalDate.now()));
		requisicao.setEmpresa(empresa);
		requisicao.setStatus(Status.PENDENTE);
		return requisicao;
	}
	
	public String entregar(Long codigo) {
		Requisicao requisicao= requisicaoRepository.findOne(codigo);
		requisicao.setStatus(Status.ENTREGUE);
		requisicaoRepository.save(requisicao);
		
		return Status.ENTREGUE.getDescricao();
	}

}
