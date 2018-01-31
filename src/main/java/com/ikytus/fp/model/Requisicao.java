package com.ikytus.fp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="requisicao")
public class Requisicao extends AbstractEntity{

	private static final long serialVersionUID = 1L;
		
	@ManyToOne
	@JoinColumn(name = "empresa")
	private Empresa empresa;
			
	@NotNull(message="Favor informar a data")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data;
			
	@Column(length=10)
	private String status;
	
	@Column(length=80)
	private String condicaopagamento;
	
	@OneToMany(mappedBy = "requisicao", cascade=CascadeType.ALL)
	private List<Item> items;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCondicaopagamento() {
		return condicaopagamento;
	}

	public void setCondicaopagamento(String condicaopagamento) {
		this.condicaopagamento = condicaopagamento;
	}

	public List<Item> getItens() {
		return items;
	}

	public void setItens(List<Item> items) {
		this.items = items;
	}
}
