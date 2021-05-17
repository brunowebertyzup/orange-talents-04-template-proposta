package br.com.zupacademy.brunoweberty.propostazup.feign;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

public class AnaliseSolicitacaoResponse {
	@NotBlank
	private String documento;
	@NotBlank
	private String nome;
	@NotBlank
	private String idProposta;
	
	@Enumerated(EnumType.STRING)
	private ResultadoSolicitacao resultadoSolicitacao;
	
	public AnaliseSolicitacaoResponse(String documento, String nome, String idProposta, ResultadoSolicitacao resultadoSolicitacao) {
		this.documento = documento;
		this.nome = nome;
		this.idProposta = idProposta;
		this.resultadoSolicitacao = resultadoSolicitacao;
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

	public ResultadoSolicitacao getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

	@Override
	public String toString() {
		return "PostResponse [documento=" + documento + ", nome=" + nome + ", idProposta=" + idProposta
				+ ", resultadoSolicitacao=" + resultadoSolicitacao + "]";
	}
}
