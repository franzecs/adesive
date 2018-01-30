package com.ikytus.fp.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ikytus.fp.model.Empresa;
import com.ikytus.fp.model.ENUM.Setores;
import com.ikytus.fp.repository.EmpresaRepository;
import com.ikytus.fp.repository.filter.Filter;
import com.ikytus.fp.util.Tools;
import com.ikytus.fp.util.pageable.pageConfig;
import com.ikytus.fp.util.security.GrupoRepository;
import com.ikytus.fp.util.security.UsuarioRepository;
import com.ikytus.fp.util.security.UsuarioService;
import com.ikytus.fp.util.security.model.Usuario;

@Controller
@RequestMapping("/administrador/usuarios")
public class UsuarioController {
	
	@Autowired
	private pageConfig<Usuario> pconfig;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private Tools tools;
		
	@GetMapping()
	public ModelAndView listar(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, @ModelAttribute("filtro") Filter filter, Pageable pageable, String ordem) {
								
		 if(tools.getGrupoMaster().hashCode()==tools.getGrupo().hashCode()) {
			Page<Usuario> listUsuarios = usuarioRepository.findByNomeContainingIgnoreCase(
					Optional.ofNullable(filter.getNome()).orElse("%"),
					pconfig.showPage(pageSize, page, pageable, Optional.ofNullable(ordem).orElse("nome")));
			return pconfig.montarPagina("security/consultaUsuario", listUsuarios, filter);
		 }
			Page<Usuario> listUsuarios = usuarioRepository.findByNomeContainingIgnoreCaseAndGruposNotContaining(
					Optional.ofNullable(filter.getNome()).orElse("%"),tools.getGrupoMaster(),
					pconfig.showPage(pageSize, page, pageable, Optional.ofNullable(ordem).orElse("nome")));
			return pconfig.montarPagina("security/consultaUsuario", listUsuarios, filter);					
	}
	
	
	@GetMapping("/novo")
	public ModelAndView novo(Usuario usuario){
		ModelAndView mv = new ModelAndView("security/cadastroUsuario");
		
		mv.addObject(usuario);
		mv.addObject("setores", Setores.values());
		
		if(tools.getGrupoMaster().hashCode()==tools.getGrupo().hashCode()) {
			mv.addObject("grupos", grupoRepository.findAll());
			mv.addObject("empresas", empresaRepository.findAll());
			return mv;
		}
		List<Empresa>empresasClientes = empresaRepository.findByFornecedor(tools.getEmpresa());
		empresasClientes.add(tools.getEmpresa());
			
		mv.addObject("grupos", grupoRepository.findByNomeNot("ROLE_MASTERROOT"));
		mv.addObject("empresas", empresasClientes);
			
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes atributos){
		if(result.hasErrors()){
			return novo(usuario);
		}
		usuarioService.salvar(usuario);
		atributos.addFlashAttribute("mensagem","Usuário salvo com sucesso!");
		return new ModelAndView("redirect:/administrador/usuarios/novo").addObject(usuario);
	}
				
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo){
		Usuario usuario = usuarioRepository.findOne(codigo); 
		usuario.setSenha(usuario.getSenha());
		return novo(usuario);
	}
	
	@DeleteMapping("/{codigo}")
	public ModelAndView deletar(@PathVariable Long codigo, RedirectAttributes atributos){
		
		usuarioRepository.delete(codigo);
		
		atributos.addFlashAttribute("mensagem","Usuário removido com sucesso!");
		return new ModelAndView("redirect:/administrador/usuarios");
	}
}
