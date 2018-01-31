package com.ikytus.fp.model.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.ikytus.fp.model.Produto;

public class ProdutoConverter implements Converter<String, Produto> {
	
	@Override
	public Produto convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Produto produto = new Produto();
			produto.setCodigo(Long.valueOf(codigo));
			return produto;
		}
		
		return null;
	}

}
