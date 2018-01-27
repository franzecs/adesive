package com.ikytus.fp.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ikytus.fp.model.Empresa;
import com.ikytus.fp.util.security.GrupoRepository;
import com.ikytus.fp.util.security.model.Grupo;
import com.ikytus.fp.util.security.model.Usuario;
import com.ikytus.fp.util.security.model.UsuarioSistema;

@Component
public class Tools {
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	public Usuario getUsuario(){
			Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication(); 
			UsuarioSistema user = (UsuarioSistema) authentication.getPrincipal();
			return user.getUsuario();
	}
	
	public Grupo getGrupo() {
		
		Grupo grupo = new Grupo();
		List<Grupo> grupos = getUsuario().getGrupos();
		
		for(Grupo g: grupos) {
			grupo = g;			
		}
		
		return grupo; 
	}
	
	public Grupo getGrupoMaster() {
		return grupoRepository.findByNome("ROLE_MASTERROOT");
	}
	
	public Empresa getEmpresa(){
		return  getUsuario().getEmpresa();
	}
	
	public Empresa getFornecedor(){
		return  getEmpresa().getFornecedor();
	}
}
