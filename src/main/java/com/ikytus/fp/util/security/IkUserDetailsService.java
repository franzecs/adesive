package com.ikytus.fp.util.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ikytus.fp.util.security.model.Grupo;
import com.ikytus.fp.util.security.model.Usuario;
import com.ikytus.fp.util.security.model.UsuarioSistema;

@Component
public class IkUserDetailsService implements UserDetailsService {
		
	@Autowired
	private UsuarioRepository usuarios;
	
	@Override
	public UserDetails loadUserByUsername(String funcional) throws UsernameNotFoundException {
		
		Usuario usuario = usuarios.findByFuncional(funcional);
		UsuarioSistema user = null;
		
		if(usuario !=null){
			user = new UsuarioSistema(usuario,getGrupos(usuario));
		}
		return user;
	}
	
	
	private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Grupo grupo: usuario.getGrupos()){
			authorities.add(new SimpleGrantedAuthority(grupo.getNome().toUpperCase()));
		}
		return authorities;
	}
}
