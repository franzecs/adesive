package com.ikytus.fp.model.ENUM;


public enum AlvoExercicio {
	
	ABDOMINAL("Abdominal"),
	ANTEBRACO("Antebraço"),
	BICEPS("Bíceps"),
	COSTA("Costa"),
	COXA("Coxa"),
	OMBROTRAPEZIO("Ombro/Trapézio"),
	PANTURRILHA("Panturrilha"),
	PEITO("Peito"),
	QUADRIL("Quadril"),
	TRICEPS("Tríceps");
	
private String descricao;
	
	AlvoExercicio(String descricao){
		this.descricao=descricao;
	}
		
	public String getDescricao() {
		return descricao;
	}

}
