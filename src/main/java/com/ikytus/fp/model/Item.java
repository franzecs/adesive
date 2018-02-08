package com.ikytus.fp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.ikytus.fp.model.ENUM.Status;

@Entity
@Table(name="item")
public class Item extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "requisicao")
	private Requisicao requisicao;
		
	@NotNull(message="Favor informar o produto")
	@ManyToOne
	@JoinColumn(name = "produto")
	private Produto produto;
	
	@Column
	@NumberFormat(pattern = "#,##0.00")
	private Double  valorunitario;
		
	@Column
	private int quantidade;
	
	@Column
	@NumberFormat(pattern = "#,##0.00")
	private Double valortotal;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataentrega;
	
	@Column(length = 80)
	private String setor;
	
	@Column(length = 100)
	private String observacao;

	public Requisicao getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Double getValorunitario() {
		return valorunitario;
	}

	public void setValorunitario(Double valorunitario) {
		this.valorunitario = valorunitario;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValortotal() {
		if(valorunitario==null) {
			return 0.00;
		}
		valortotal = valorunitario * quantidade;
		return valortotal;
	}

	public void setValortotal(Double valortotal) {
		this.valortotal = valortotal;
	}

	public Date getDataentrega() {
		return dataentrega;
	}

	public void setDataentrega(Date dataentrega) {
		this.dataentrega = dataentrega;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public boolean isEntregue() {
		if(requisicao==null) {
			return false;
		}
		return Status.ENTREGUE.equals(this.requisicao.getStatus());
	}
}
