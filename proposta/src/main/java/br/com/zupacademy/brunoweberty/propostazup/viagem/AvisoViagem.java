package br.com.zupacademy.brunoweberty.propostazup.viagem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.zupacademy.brunoweberty.propostazup.cartao.Cartao;

@Entity
public class AvisoViagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String destino;
	private LocalDate dataTermino;
	private LocalDateTime criadoEm = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
	private String ipSolicitante;
	private String agenteDoUsuario;
	@ManyToOne(cascade = CascadeType.MERGE)
	private Cartao cartao;

	@Deprecated
	public AvisoViagem() {
	}

	public AvisoViagem(String destino, LocalDate dataTermino, String ipSolicitante, String agenteDoUsuario,
			Cartao cartao) {
		this.destino = destino;
		this.dataTermino = dataTermino;
		this.ipSolicitante = ipSolicitante;
		this.agenteDoUsuario = agenteDoUsuario;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}

	public String getIpSolicitante() {
		return ipSolicitante;
	}

	public String getAgenteDoUsuario() {
		return agenteDoUsuario;
	}

	public Cartao getCartao() {
		return cartao;
	}
}
