package br.com.zupacademy.brunoweberty.propostazup.proposta;

import java.math.BigDecimal;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.brunoweberty.propostazup.cartao.Cartao;
import br.com.zupacademy.brunoweberty.propostazup.validations.annotations.CPForCNPJ;

public class PropostaRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;
	
	@Email
	@NotBlank
	private String email;

	@NotBlank
	@CPForCNPJ
	private String documento;

	@NotBlank
	private String endereco;

	@NotNull
	@Positive
	private BigDecimal salario;

	@Enumerated(EnumType.STRING)
	private StatusProposta statusProposta;
	
	private Cartao cartao;

	public PropostaRequest(String nome, String email, String documento, String endereco, BigDecimal salario) {
		this.nome = nome;
		this.email = email;
		this.documento = documento;
		this.endereco = endereco;
		this.salario = salario;
	}

	public PropostaRequest(Proposta p) {
		this.id = p.getId();
		this.nome = p.getNome();
		this.email = p.getEmail();
		this.documento = p.getDocumento();
		this.endereco = p.getEndereco();
		this.salario = p.getSalario();
		this.statusProposta = p.getStatusProposta();
		this.cartao = p.getCartao();
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

	public Proposta converterEmProposta() {
		return new Proposta(this.nome, this.email, this.documento, this.endereco, this.salario);
	}
}
