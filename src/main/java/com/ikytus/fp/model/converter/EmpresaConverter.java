package com.ikytus.fp.model.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.ikytus.fp.model.Empresa;

public class EmpresaConverter implements Converter<String, Empresa> {
	
	@Override
	public Empresa convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Empresa empresa = new Empresa();
			empresa.setCodigo(Long.valueOf(codigo));
			return empresa;
		}
		
		return null;
	}

}
