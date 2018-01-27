package com.ikytus.fp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="empresa")
public class Empresa extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message="Favor informar o nome da Empresa")
	@Column(length = 80)
	private String nome;
	
	@NotNull(message="Favor informar a data de adesão")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataadesao;
	
	@Column(length=14)
    private String cnpj;
			
	@Column(length=100)
    private String email;
    
    @Column(length=16)
    @NotBlank(message="Favor informar o telefone de contato")
    private String telefone;
    
    @Column(length=500)
    @NotBlank(message="Favor informar o endereço")
    private String endereco;
    
    @Column(length=500)
    private String observacao;
    
    @ManyToOne
    @JoinColumn(name = "fornecedor")
    private Empresa fornecedor;
    
    @Column
    private Boolean cliente; 
    	
	@OneToMany(mappedBy = "empresa")
	private List<Produto> produtos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataadesao() {
		return dataadesao;
	}

	public void setDataadesao(Date dataadesao) {
		this.dataadesao = dataadesao;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Empresa getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Empresa fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Boolean getCliente() {
		return cliente;
	}

	public void setCliente(Boolean cliente) {
		this.cliente = cliente;
	}
	
}
