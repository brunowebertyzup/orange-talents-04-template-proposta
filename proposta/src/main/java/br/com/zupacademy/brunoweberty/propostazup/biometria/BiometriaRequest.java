package br.com.zupacademy.brunoweberty.propostazup.biometria;

import java.util.Base64;

import javax.validation.constraints.NotEmpty;

import br.com.zupacademy.brunoweberty.propostazup.cartao.Cartao;

public class BiometriaRequest {
	
	@NotEmpty
	private byte[] biometria;
	
	public BiometriaRequest(byte[] biometria) {
		this.biometria = biometria;
	}
	
	@Deprecated
	public BiometriaRequest() {
		super();
	}

	public Biometria converterEmBiometria(Cartao cartao, byte[] biometria) {
		String stringBiometria = Base64.getEncoder().encodeToString(biometria);
		return new Biometria(cartao, stringBiometria);
	}
	
	public byte[] getBiometria() {
		return biometria;
	}	
}
