package br.com.zupacademy.brunoweberty.propostazup.viagem;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import br.com.zupacademy.brunoweberty.propostazup.cartao.Cartao;

public class AvisoViagemRequest {

	@NotBlank
	private String destino;
	@NotNull
	private LocalDate validoAte;

	public AvisoViagemRequest(String destino, LocalDate validoAte) {
		this.destino = destino;
		this.validoAte = validoAte;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getDataTermino() {
		return validoAte;
	}

	@Override
	public String toString() {
		return "AvisoViagemRequest{" + "destino='" + destino + '\'' + ", validoAte=" + validoAte + '}';
	}

	public AvisoViagem toModel(Cartao cartao, String ipCliente, String agenteUsuario) {
		Assert.notNull(cartao, "Cartão não deveria ser nulo");
		Assert.hasLength(ipCliente, "IP do Cliente não deveria ser vazio");
		Assert.hasLength(agenteUsuario, "User Agent não deveria ser vazio.");
		return new AvisoViagem(this.destino, this.validoAte, ipCliente, agenteUsuario, cartao);
	}

}
