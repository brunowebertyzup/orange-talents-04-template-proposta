package br.com.zupacademy.brunoweberty.propostazup.carteiraPaypal;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class ResultadoCarteira {
	
	private String id;
	
	@Enumerated(EnumType.STRING)
	private Resultado resultado;
	
	public ResultadoCarteira() {}

	public ResultadoCarteira(String id, Resultado resultado) {
		this.id = id;
		this.resultado = resultado;
	}

	public String getId() {
		return id;
	}

	public Resultado getResultado() {
		return resultado;
	}
}
