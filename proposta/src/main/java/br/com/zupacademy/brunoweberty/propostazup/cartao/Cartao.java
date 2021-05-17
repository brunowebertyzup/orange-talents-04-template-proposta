package br.com.zupacademy.brunoweberty.propostazup.cartao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.zupacademy.brunoweberty.propostazup.biometria.Biometria;

@Entity
public class Cartao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String numeroCartao;
	
	private String titular;
	
	@OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
	private List<Biometria> biometrias;
	
	@Deprecated
	public Cartao() {
	}
	
	public Cartao(String numeroCartao, String titular) {
		this.numeroCartao = numeroCartao;
		this.titular = titular;
	}
	
	public Long getId() {
		return id;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public String getTitular() {
		return titular;
	}

	public List<Biometria> getBiometrias() {
		return biometrias;
	}
}
