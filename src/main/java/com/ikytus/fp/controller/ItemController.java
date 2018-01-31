package com.ikytus.fp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ikytus.fp.model.Item;
import com.ikytus.fp.model.Requisicao;
import com.ikytus.fp.repository.ItemRepository;
import com.ikytus.fp.repository.ProdutoRepository;
import com.ikytus.fp.service.ItemService;
import com.ikytus.fp.util.Tools;
import com.ikytus.fp.util.img.GravarImagem;
import com.ikytus.fp.util.pageable.pageConfig;

@Controller
@RequestMapping("itens")
public class ItemController {
		
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
			
	@Autowired
	Tools tools;
		
	/*@GetMapping()
	public ModelAndView listar(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, @ModelAttribute("filtro") Filter filter, Pageable pageable, String ordem) {
		
		Page<Item> listitens = itemRepository.findByStatus(
				Optional.ofNullable(filter.getStatus()).orElse("Pendente"),
				pconfig.showPage(pageSize, page, pageable, Optional.ofNullable(ordem).orElse("requisicao")));
			
		return pconfig.montarPagina("masterroot/consultaItens", listitens, filter);
	}*/
	
	
	@GetMapping("/novo")
	public ModelAndView novo(Item item){
		ModelAndView mv = new ModelAndView("administrador/cadastroItem");
		mv.addObject("listaProdutos", produtoRepository.findByEmpresa(tools.getFornecedor()));
		mv.addObject(item);
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@RequestParam(name="requisicao", required=false) Requisicao requisicao, 
								@Valid Item item, BindingResult result, RedirectAttributes atributos){
		
		if(result.hasErrors()){return novo(item);}
		
		itemService.salvar(item,requisicao);
			
		atributos.addFlashAttribute("mensagem","Item salvo com sucesso!");				
		
		return new ModelAndView("redirect:/itens/novo").addObject(requisicao);
	}
				
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo){
		Item item = itemRepository.findOne(codigo); 
		return novo(item);
	}
	
	@DeleteMapping("/{codigo}")
	public ModelAndView deletar(@PathVariable Long codigo, RedirectAttributes atributos){
		Item item = itemRepository.findOne(codigo);
		String codigoRequisicao= String.valueOf(item.getRequisicao().getCodigo());
		itemService.deletar(codigo);
		
		atributos.addFlashAttribute("mensagem","Item removido com sucesso!");
		return new ModelAndView("redirect:/requisicoes/"+codigoRequisicao);
	}
}
