package br.com.zupacademy.brunoweberty.propostazup.carteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.util.Assert;

import br.com.zupacademy.brunoweberty.propostazup.cartao.Cartao;

public class CarteiraRequest {

	@NotBlank
	@Email
	private String email;
	private String carteira;

	public CarteiraRequest() {
	}

	public CarteiraRequest(String email, String carteira) {
		this.email = email;
		this.carteira = carteira;
	}

	public String getEmail() {
		return email;
	}

	public String getCarteira() {
		return carteira;
	}

	public Carteira toModel(Cartao cartao) {
		Assert.hasLength(email, "E-mail da carteira não deveria ser vazio.");
		return new Carteira(this.email, this.carteira, cartao);
	}
}
