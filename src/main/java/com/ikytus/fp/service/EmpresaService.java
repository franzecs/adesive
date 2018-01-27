package com.ikytus.fp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ikytus.fp.model.Empresa;
import com.ikytus.fp.repository.EmpresaRepository;

@Service
public class EmpresaService implements AbstractService<Empresa>{
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	public void salvar(Empresa empresa){
		this.empresaRepository.save(empresa);
	}
		
	public void salvar(Empresa empresa, String imagem){
		empresa.setImg(imagem);
		this.empresaRepository.save(empresa);
	}
	
	public void deletar(Empresa empresa) {
		empresaRepository.delete(empresa);
	}

}
