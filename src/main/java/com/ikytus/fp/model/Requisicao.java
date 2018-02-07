package com.ikytus.fp.model;

import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.ikytus.fp.model.ENUM.Status;

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
			
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Column(length=80)
	private String condicaopagamento;
	
	@OneToMany(mappedBy = "requisicao", cascade=CascadeType.ALL)
	private List<Item> items;
	
	public String getValor() {
		String valor = NumberFormat.getCurrencyInstance().format(getItems().stream()
				.mapToDouble(p ->p.getValortotal()).sum());
		return valor;
	}

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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getCondicaopagamento() {
		return condicaopagamento;
	}

	public void setCondicaopagamento(String condicaopagamento) {
		this.condicaopagamento = condicaopagamento;
	}
			
	public boolean isPendente() {
		return Status.PENDENTE.equals(this.status);
	}
	
	public boolean isCancelado() {
		return Status.CANCELADO.equals(this.status);
	}
	
	public boolean isEntregue() {
		return Status.ENTREGUE.equals(this.status);
	}
}
