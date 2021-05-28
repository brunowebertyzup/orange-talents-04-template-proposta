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
	private LocalDate dataTermino;

	public AvisoViagemRequest(String destino, LocalDate dataTermino) {
		this.destino = destino;
		this.dataTermino = dataTermino;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}

	@Override
	public String toString() {
		return "AvisoViagemRequest{" + "destino='" + destino + '\'' + ", dataTermino=" + dataTermino + '}';
	}

	public AvisoViagem toModel(Cartao cartao, String ipCliente, String agenteUsuario) {
		Assert.notNull(cartao, "Cartão não deveria ser nulo");
		Assert.hasLength(ipCliente, "IP do Cliente não deveria ser vazio");
		Assert.hasLength(agenteUsuario, "User Agent não deveria ser vazio.");
		return new AvisoViagem(this.destino, this.dataTermino, ipCliente, agenteUsuario, cartao);
	}

}
