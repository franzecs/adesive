package com.ikytus.fp.controller;

import java.sql.Date;
import java.text.NumberFormat;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ikytus.fp.model.Requisicao;
import com.ikytus.fp.model.ENUM.Status;
import com.ikytus.fp.repository.EmpresaRepository;
import com.ikytus.fp.repository.RequisicaoRepository;
import com.ikytus.fp.repository.filter.Filter;
import com.ikytus.fp.service.RequisicaoService;
import com.ikytus.fp.util.Tools;
import com.ikytus.fp.util.pageable.pageConfig;

@Controller
@RequestMapping("requisicoes")
public class RequisicaoController {
		
	@Autowired
	private RequisicaoService requisicaoService;
	
	@Autowired
	private RequisicaoRepository requisicaoRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private pageConfig<Requisicao> pconfig;
		
	@Autowired
	Tools tools;
				
	@GetMapping()
	public ModelAndView listar(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, @ModelAttribute("filtro") Filter filter, Pageable pageable, String ordem) {
		
		if(filter.getStatus()==null) {
			Page<Requisicao> listrequisicoes = requisicaoRepository.findByEmpresa(tools.getEmpresa(), 
					pconfig.showPage(pageSize, page, pageable, Optional.ofNullable(ordem).orElse("empresa")));
			return pconfig.montarPagina("cliente/consultaRequisicao", listrequisicoes, filter);
		}
		
		Page<Requisicao> listrequisicoes = requisicaoRepository.findByEmpresaAndStatus(
				tools.getEmpresa(), filter.getStatus(), 
				pconfig.showPage(pageSize, page, pageable, Optional.ofNullable(ordem).orElse("empresa")));
			
		return pconfig.montarPagina("cliente/consultaRequisicao", listrequisicoes, filter);
	}
	
	@GetMapping("/novo")
	public ModelAndView novo(Requisicao requisicao){
		ModelAndView mv = new ModelAndView("cliente/cadastroRequisicao");
		
		if(requisicao.getCodigo()==null) {
			requisicao.setData(Date.valueOf(LocalDate.now()));
			requisicao.setEmpresa(tools.getEmpresa());
			requisicao.setStatus(Status.PENDENTE);
		}
		
		if(requisicao.getCodigo() != null){
			mv.addObject("listaItens", requisicao.getItems());
			mv.addObject("valor",NumberFormat.getCurrencyInstance().format(requisicao.getItems().stream()
					.mapToDouble(p ->p.getValortotal()).sum()));
		}
		
		mv.addObject("listaStatus", Status.values());
		mv.addObject("empresas", empresaRepository.findAll());
		mv.addObject(requisicao);
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Requisicao requisicao, BindingResult result, RedirectAttributes atributos){
		
		if(result.hasErrors()){return novo(requisicao);}
		requisicaoService.salvar(requisicao);		
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
	
	@PutMapping("/{codigo}/entregar")
	public @ResponseBody String entregar(@PathVariable Long codigo) {
		return requisicaoService.entregar(codigo);
	}
}
