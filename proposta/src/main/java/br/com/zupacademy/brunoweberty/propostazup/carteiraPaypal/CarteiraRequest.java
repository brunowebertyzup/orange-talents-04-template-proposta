package br.com.zupacademy.brunoweberty.propostazup.carteiraPaypal;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import br.com.zupacademy.brunoweberty.propostazup.cartao.Cartao;

public class CarteiraRequest {

	@NotBlank
	@Email
	private String email;
	@NotNull @Enumerated(EnumType.STRING)
	private TipoCarteira carteira;

	public CarteiraRequest() {
	}

	public CarteiraRequest(String email, TipoCarteira carteira) {
		this.email = email;
		this.carteira = carteira;
	}

	public String getEmail() {
		return email;
	}

	public TipoCarteira getCarteira() {
		return carteira;
	}

	public void setCarteira(TipoCarteira carteira) {
		this.carteira = carteira;
	}

	public Carteira toModel(Cartao cartao) {
		Assert.hasLength(email, "E-mail da carteira não deveria ser vazio.");
		return new Carteira(this.email, this.carteira, cartao);
	}
}
