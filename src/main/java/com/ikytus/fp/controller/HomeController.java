package com.ikytus.fp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ikytus.fp.util.security.model.Usuario;
import com.ikytus.fp.util.security.model.UsuarioSistema;

@Controller
@RequestMapping("/fp")
public class HomeController {
	
	@GetMapping()
	public ModelAndView home (){
		ModelAndView mv = new ModelAndView("home");
		return mv;
	}
	
	public Usuario getUsuario(){
		Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication(); 
		UsuarioSistema user = (UsuarioSistema) authentication.getPrincipal();
		Usuario usuario = user.getUsuario();
		
		return usuario;
	}
}