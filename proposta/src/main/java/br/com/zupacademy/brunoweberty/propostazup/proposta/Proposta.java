package br.com.zupacademy.brunoweberty.propostazup.proposta;

import java.math.BigDecimal;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.zupacademy.brunoweberty.propostazup.cartao.Cartao;
import br.com.zupacademy.brunoweberty.propostazup.feignProposta.AnaliseSolicitacaoRequest;
import br.com.zupacademy.brunoweberty.propostazup.seguranca.EncryptToDatabase;

@Entity
public class Proposta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String email;
	
	@Convert(converter = EncryptToDatabase.class)
	private String documento;
	
	private String endereco;
	
	private BigDecimal salario;
	
	private StatusProposta statusProposta = StatusProposta.NAO_ANALISADO;
	
	@OneToOne
	private Cartao cartao;
	
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
	
	public Cartao getCartao() {
		return cartao;
	}
	
	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public void setStatusProposta(StatusProposta statusProposta) {
		this.statusProposta = statusProposta;
	}

	public AnaliseSolicitacaoRequest converterEmPostRequest() {
		return new AnaliseSolicitacaoRequest(this.documento, this.nome, this.id.toString());
	}
}
