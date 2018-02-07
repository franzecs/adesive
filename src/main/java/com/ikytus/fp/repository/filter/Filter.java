package com.ikytus.fp.repository.filter;

import java.util.Date;

import com.ikytus.fp.model.Empresa;
import com.ikytus.fp.model.ENUM.Status;

public class Filter {
	
	private String nome;
	
	private Status status;
	
	private Empresa empresa;
		
	private Date datainicial;
	
	private Date datafinal;

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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Date getDatainicial() {
		return datainicial;
	}

	public void setDatainicial(Date datainicial) {
		this.datainicial = datainicial;
	}

	public Date getDatafinal() {
		return datafinal;
	}

	public void setDatafinal(Date datafinal) {
		this.datafinal = datafinal;
	}
}
