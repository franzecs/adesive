package com.ikytus.fp.controller;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import javax.servlet.http.Part;
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

import com.ikytus.fp.model.Produto;
import com.ikytus.fp.repository.ProdutoRepository;
import com.ikytus.fp.repository.filter.Filter;
import com.ikytus.fp.service.ProdutoService;
import com.ikytus.fp.util.Tools;
import com.ikytus.fp.util.img.GravarImagem;
import com.ikytus.fp.util.pageable.pageConfig;

@Controller
@RequestMapping("/administrador/produtos")
public class ProdutoController {
		
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private pageConfig<Produto> pconfig;
	
	@Autowired
	private GravarImagem gravarImagem;
	
	@Autowired
	Tools tools;
	
	Part arquivo;
		
	@GetMapping()
	public ModelAndView listar(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, @ModelAttribute("filtro") Filter filter, Pageable pageable, String ordem) {
		
		Page<Produto> listprodutos = produtoRepository.findByNomeContainingAndEmpresa(
				Optional.ofNullable(filter.getNome()).orElse("%"), tools.getEmpresa(),
				pconfig.showPage(pageSize, page, pageable, Optional.ofNullable(ordem).orElse("nome")));
			
		return pconfig.montarPagina("administrador/consultaProdutos", listprodutos, filter);
	}
	
	
	@GetMapping("/novo")
	public ModelAndView novo(Produto produto){
		ModelAndView mv = new ModelAndView("administrador/cadastroProdutos");
		mv.addObject(produto);
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@RequestParam("file") MultipartFile file, 
								@Valid Produto produto, BindingResult result, RedirectAttributes atributos){
		
		if(result.hasErrors()){return novo(produto);}
		produto.setEmpresa(tools.getEmpresa());
		gravarImagem.gravaImagemBase64Service(file, produtoService, produto);
		
		atributos.addFlashAttribute("mensagem","Produto salvo com sucesso!");				
		
		return new ModelAndView("redirect:/administrador/produtos/novo");
	}
				
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo){
		Produto produto = produtoRepository.findOne(codigo); 
		return novo(produto);
	}
	
	@DeleteMapping("/{codigo}")
	public ModelAndView deletar(@PathVariable Long codigo, RedirectAttributes atributos){
		
		produtoRepository.delete(codigo);
		
		atributos.addFlashAttribute("mensagem","Produto removido com sucesso!");
		return new ModelAndView("redirect:/administrador/produtos");
	}
	
	@PostMapping("/gravarcsv")
	public ModelAndView gravaCSV(@RequestParam("file") Part arquivo) throws IOException {
		
		Scanner scanner = new Scanner(arquivo.getInputStream(), "UTF-8");
		scanner.useDelimiter(";");

		while(scanner.hasNext()) {
			String linha = scanner.nextLine();
			if(linha !=null && !linha.trim().isEmpty()) {
				linha = linha.replace("\"", "");
				String[] dados = linha.split("\\;");
				System.out.println("nome: " + dados[0] + " e o email: " + dados[1]);
			}
		}
		scanner.close();
						
		return new ModelAndView("redirect:/administrador/produtos");
	}
}
