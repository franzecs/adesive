package com.ikytus.fp.repository.filter;

import com.ikytus.fp.model.ENUM.Status;

public class Filter {
	
	private String nome;
	
	private Status status;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
