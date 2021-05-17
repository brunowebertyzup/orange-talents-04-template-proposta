package br.com.zupacademy.brunoweberty.propostazup.biometria;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import br.com.zupacademy.brunoweberty.propostazup.cartao.Cartao;

@Entity
public class Biometria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreationTimestamp
	private LocalDateTime dataCriacao = LocalDateTime.now(ZoneOffset.UTC);
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Cartao cartao;
	
	@Lob
	private String biometria;

	public Biometria(Cartao cartao, String biometria) {
		this.cartao = cartao;
		this.biometria = biometria;
	}

	@Deprecated
	public Biometria() {
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public String getBiometria() {
		return biometria;
	}
	
}
