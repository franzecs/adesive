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
import com.ikytus.fp.repository.ItemRepository;
import com.ikytus.fp.repository.ProdutoRepository;
import com.ikytus.fp.service.ItemService;
import com.ikytus.fp.util.Tools;

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
		
	@GetMapping("/novo")
	public ModelAndView novo(@RequestParam(name="codigoRequisicao", required=false) String codigoRequisicao, Item item){
		ModelAndView mv = new ModelAndView("cliente/cadastroItem");
		
		if(tools.getGrupoAdmLoc().hashCode()==tools.getGrupo().hashCode()) {
			mv.addObject("listaProdutos", produtoRepository.findByEmpresa(tools.getEmpresa()));
		}else {
			mv.addObject("listaProdutos", produtoRepository.findByEmpresa(tools.getFornecedor()));
		}
				
		mv.addObject(item);
		mv.addObject("codigoRequisicao", codigoRequisicao);
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@RequestParam(name="codigoRequisicao", required=false) String codigoRequisicao, 
								@Valid Item item, BindingResult result, RedirectAttributes atributos){
		
		if(result.hasErrors()){return novo(codigoRequisicao, item);}
		itemService.salvar(codigoRequisicao,item);
		atributos.addFlashAttribute("mensagem","Item salvo com sucesso!");				
		return new ModelAndView("redirect:/itens/novo").addObject(item).addObject("codigoRequisicao", codigoRequisicao);
	}
				
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo){
		Item item = itemRepository.findOne(codigo); 
		String codigoRequisicao= String.valueOf(item.getRequisicao().getCodigo());
		return novo(codigoRequisicao,item);
	}
	
	@DeleteMapping("/{codigo}")
	public ModelAndView deletar(@PathVariable Long codigo, RedirectAttributes atributos){
		itemService.deletar(codigo);
		atributos.addFlashAttribute("mensagem","Item removido com sucesso!");
		return new ModelAndView("redirect:/requisicoes/");
	}
}
