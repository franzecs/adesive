package com.ikytus.fp.controller;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ikytus.fp.repository.filter.Filter;
import com.ikytus.fp.util.img.GravarImagem;
import com.ikytus.fp.util.pageable.pageConfig;
import com.ikytus.fp.repository.EmpresaRepository;
import com.ikytus.fp.service.EmpresaService;
import com.ikytus.fp.model.Empresa;

@Controller
@RequestMapping("/masterroot/empresas")
public class EmpresaController {
		
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private pageConfig<Empresa> pconfig;
	
	@Autowired
	private GravarImagem gravarImagem;
		
	@GetMapping()
	public ModelAndView listar(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, @ModelAttribute("filtro") Filter filter, Pageable pageable, String ordem) {
				
		Page<Empresa> listEmpresas = empresaRepository.findByNomeContainingIgnoreCase(
				Optional.ofNullable(filter.getNome()).orElse("%"),
				pconfig.showPage(pageSize, page, pageable, Optional.ofNullable(ordem).orElse("nome")));
			
		return pconfig.montarPagina("masterroot/consultaEmpresas", listEmpresas, filter);
	}
	
	
	@GetMapping("/novo")
	public ModelAndView novo(Empresa empresa){
		ModelAndView mv = new ModelAndView("masterroot/cadastroEmpresas");
		mv.addObject(empresa);
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@RequestParam("file") MultipartFile file, 
								@Valid Empresa empresa, BindingResult result, RedirectAttributes atributos){
		
		if(result.hasErrors()){return novo(empresa);}
		
		gravarImagem.gravaImagemBase64Service(file, empresaService, empresa);
		
		atributos.addFlashAttribute("mensagem","Empresa salva com sucesso!");				
		
		return new ModelAndView("redirect:/masterroot/empresas/novo").addObject(empresa);
	}
				
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo){
		Empresa empresa = empresaRepository.findOne(codigo); 
		return novo(empresa);
	}
	
	@DeleteMapping("/{codigo}")
	public ModelAndView deletar(@PathVariable Long codigo, RedirectAttributes atributos){
		
		empresaRepository.delete(codigo);
		
		atributos.addFlashAttribute("mensagem","Empresa removida com sucesso!");
		return new ModelAndView("redirect:/masterroot/empresas");
	}
}
