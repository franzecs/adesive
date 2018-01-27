package com.ikytus.fp.model.ENUM;

public enum Sequencia {
	SEQA("Seq-A"),
	SEQB("Seq-B"),
	SEQC("Seq-C"),
	SEQD("Seq-D"),
	SEQE("Seq-E"),
	SEQF("Seq-F"),
	SEQ0("---");
	
	private String descricao;
	
	Sequencia(String descricao){
		this.descricao=descricao;
	}
		
	public String getDescricao() {
		return descricao;
	}

}
