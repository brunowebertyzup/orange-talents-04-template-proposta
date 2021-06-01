package br.com.zupacademy.brunoweberty.propostazup.carteiraPaypal;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.brunoweberty.propostazup.cartao.Cartao;

@Entity
public class Carteira {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	@ManyToOne(cascade = CascadeType.MERGE)
	private Cartao cartao;
	@NotNull @Enumerated(EnumType.STRING)
    private TipoCarteira tipoCarteira;
	private LocalDateTime associadaEm = LocalDateTime.now();
	
	public Carteira() {
	}

	public Carteira(String email, TipoCarteira tipoCarteira, Cartao cartao) {
		this.email = email;
		this.tipoCarteira = tipoCarteira;
		this.cartao = cartao;
	}
	
	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public TipoCarteira getTipoCarteira() {
		return tipoCarteira;
	}

	public void setTipoCarteira(TipoCarteira tipoCarteira) {
		this.tipoCarteira = tipoCarteira;
	}

	public LocalDateTime getAssociadaEm() {
		return associadaEm;
	}

	public void setAssociadaEm(LocalDateTime associadaEm) {
		this.associadaEm = associadaEm;
	}
}
