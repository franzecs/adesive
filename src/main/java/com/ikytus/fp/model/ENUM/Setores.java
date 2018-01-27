package com.ikytus.fp.model.ENUM;

public enum Setores {

	ADMINISTRATIVO("ADMINISTRATIVO"),
	ALUNOMUSCULACAO("ALUNO MUSCULAÇÃO"), 
	ALUNOHIDRO("ALUNO HIDROGINÁSTICA"),
	IKTUS("IKTUS"),
	INSTRUTORES("INSTRUTORES");

	private String descricao;
	
	Setores(String descricao){
		this.descricao=descricao;
	}
		
	public String getDescricao() {
		return descricao;
	}
}
