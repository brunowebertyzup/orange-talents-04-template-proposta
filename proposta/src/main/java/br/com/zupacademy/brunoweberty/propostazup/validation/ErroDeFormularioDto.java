package br.com.zupacademy.brunoweberty.propostazup.validation;

public class ErroDeFormularioDto {
	private String field;
	private String error;

	public ErroDeFormularioDto(String field, String error) {
		this.field = field;
		this.error = error;
	}

	public String getField() {
		return field;
	}

	public String getError() {
		return error;
	}
}
