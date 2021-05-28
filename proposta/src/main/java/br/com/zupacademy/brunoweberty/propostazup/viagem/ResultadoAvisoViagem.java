package br.com.zupacademy.brunoweberty.propostazup.viagem;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class ResultadoAvisoViagem {
	
	@Enumerated(EnumType.STRING)
	private Resultado resultado;
	
	public ResultadoAvisoViagem() {}
	
	public ResultadoAvisoViagem(Resultado resultado) {
		this.resultado = resultado;
	}

	public Resultado getResultado() {
		return resultado;
	}
}
