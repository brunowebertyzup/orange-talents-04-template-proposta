package br.com.zupacademy.brunoweberty.propostazup.proposta;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.zupacademy.brunoweberty.propostazup.feign.PostRequest;

@Entity
public class Proposta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String email;
	
	private String documento;
	
	private String endereco;
	
	private BigDecimal salario;
	
	private StatusProposta statusProposta = StatusProposta.NAO_ANALISADO;
	
	private String numeroCartao;
	
	@Deprecated
	public Proposta() {
	}

	public Proposta(String nome, String email, String documento, String endereco, BigDecimal salario) {
		this.nome = nome;
		this.email = email;
		this.documento = documento;
		this.endereco = endereco;
		this.salario = salario;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}
	
	public String getDocumento() {
		return documento;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}
	
	public StatusProposta getStatusProposta() {
		return statusProposta;
	}
	
	public String getNumeroCartao() {
		return numeroCartao;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public void setStatusProposta(StatusProposta statusProposta) {
		this.statusProposta = statusProposta;
	}

	public PostRequest converterEmPostRequest() {
		return new PostRequest(this.documento, this.nome, this.id.toString());
	}
}
