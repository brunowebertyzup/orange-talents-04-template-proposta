package br.com.zupacademy.brunoweberty.propostazup.feignCartao;

import br.com.zupacademy.brunoweberty.propostazup.cartao.Cartao;

public class CartaoResponse {
	
	private String id;
	
	private String titular;
	
	private String idProposta;

	public CartaoResponse(String id, String titular, String idProposta) {
		this.id = id;
		this.titular = titular;
		this.idProposta = idProposta;
	}

	public String getId() {
		return id;
	}

	public String getTitular() {
		return titular;
	}

	public String getIdProposta() {
		return idProposta;
	}
	
	public Cartao converterEmCartao() {
		return new Cartao(this.id, this.titular);
	}
}
