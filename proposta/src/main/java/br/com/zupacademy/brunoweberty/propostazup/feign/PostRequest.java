package br.com.zupacademy.brunoweberty.propostazup.feign;

public class PostRequest {

	private String documento;
	private String nome;
	private String idProposta;

	public PostRequest(String documento, String nome, String idProposta) {
		this.documento = documento;
		this.nome = nome;
		this.idProposta = idProposta;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getIdProposta() {
		return idProposta;
	}

}
