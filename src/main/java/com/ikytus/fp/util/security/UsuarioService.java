package com.ikytus.fp.util.security;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ikytus.fp.util.security.model.Usuario;

@Service
public class UsuarioService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UsuarioRepository usuarios;

	
	public void salvar(Usuario usuario){
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		usuario.setNome(usuario.getNome().toUpperCase());
		this.usuarios.save(usuario);
	}
}
