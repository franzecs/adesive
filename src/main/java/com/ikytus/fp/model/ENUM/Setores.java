package com.ikytus.fp.model.ENUM;

public enum Setores {

	ADMINISTRATIVO("ADMINISTRATIVO");

	private String descricao;
	
	Setores(String descricao){
		this.descricao=descricao;
	}
		
	public String getDescricao() {
		return descricao;
	}
}
