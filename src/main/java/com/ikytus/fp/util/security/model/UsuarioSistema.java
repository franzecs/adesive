package com.ikytus.fp.util.security.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UsuarioSistema extends User {
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;

	public UsuarioSistema(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getFuncional(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public String getPermissao() {
		String gr="";
		for(Grupo grupo : usuario.getGrupos()) {
			 gr = grupo.getNome();
		}
		return gr;
	}
}
