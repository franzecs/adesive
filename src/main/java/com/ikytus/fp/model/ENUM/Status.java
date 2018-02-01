package com.ikytus.fp.model.ENUM;

public enum Status {
	
	ORCAMENTO("Orçamento"),
	PENDENTE("Pendente"), 
	ENTREGUE("Entregue"),
	CANCELADO("Cancelado");

	private String descricao;
	
	Status(String descricao){
		this.descricao=descricao;
	}
		
	public String getDescricao() {
		return descricao;
	}
}
