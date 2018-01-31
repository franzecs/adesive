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
import com.ikytus.fp.util.Tools;
import com.ikytus.fp.util.img.GravarImagem;
import com.ikytus.fp.util.pageable.pageConfig;
import com.ikytus.fp.repository.RequisicaoRepository;
import com.ikytus.fp.service.RequisicaoService;
import com.ikytus.fp.model.Requisicao;

@Controller
@RequestMapping("requisicoes")
public class RequisicaoController {
		
	@Autowired
	private RequisicaoService requisicaoService;
	
	@Autowired
	private RequisicaoRepository requisicaoRepository;
	
	@Autowired
	private pageConfig<Requisicao> pconfig;
	
	@Autowired
	private GravarImagem gravarImagem;
	
	@Autowired
	Tools tools;
		
	@GetMapping()
	public ModelAndView listar(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, @ModelAttribute("filtro") Filter filter, Pageable pageable, String ordem) {
		
		Page<Requisicao> listrequisicoes = requisicaoRepository.findByStatusAndEmpresa(
				Optional.ofNullable(filter.getNome()).orElse("%"), tools.getEmpresa(),
				pconfig.showPage(pageSize, page, pageable, Optional.ofNullable(ordem).orElse("nome")));
			
		return pconfig.montarPagina("administrador/consultaRequisicao", listrequisicoes, filter);
	}
	
	
	@GetMapping("/novo")
	public ModelAndView novo(Requisicao requisicao){
		ModelAndView mv = new ModelAndView("administrador/cadastroRequisicao");
		mv.addObject(requisicao);
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@RequestParam("file") MultipartFile file, 
								@Valid Requisicao requisicao, BindingResult result, RedirectAttributes atributos){
		
		if(result.hasErrors()){return novo(requisicao);}
		requisicao.setEmpresa(tools.getEmpresa());
		gravarImagem.gravaImagemBase64Service(file, requisicaoService, requisicao);
		
		atributos.addFlashAttribute("mensagem","Requisicao salva com sucesso!");				
		
		return new ModelAndView("redirect:/requisicoes/novo").addObject(requisicao);
	}
				
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo){
		Requisicao requisicao = requisicaoRepository.findOne(codigo); 
		return novo(requisicao);
	}
	
	@DeleteMapping("/{codigo}")
	public ModelAndView deletar(@PathVariable Long codigo, RedirectAttributes atributos){
		
		requisicaoService.deletar(codigo);
		
		atributos.addFlashAttribute("mensagem","Requisição removida com sucesso!");
		return new ModelAndView("redirect:/requisicoes");
	}
}
