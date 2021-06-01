package br.com.zupacademy.brunoweberty.propostazup.carteira;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.zupacademy.brunoweberty.propostazup.cartao.Cartao;

@Entity
public class Carteira {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String carteira;
	@ManyToOne(cascade = CascadeType.MERGE)
	private Cartao cartao;

	public Carteira() {
	}

	public Carteira(String email, String carteira, Cartao cartao) {
		super();
		this.email = email;
		this.carteira = carteira;
		this.cartao = cartao;
	}
	
	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getCarteira() {
		return carteira;
	}

	public Cartao getCartao() {
		return cartao;
	}

}
