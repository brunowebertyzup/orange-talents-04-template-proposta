package br.com.zupacademy.brunoweberty.propostazup.cartao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.zupacademy.brunoweberty.propostazup.biometria.Biometria;
import br.com.zupacademy.brunoweberty.propostazup.bloqueio.Bloqueio;

@Entity
public class Cartao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String numeroCartao;
	
	private String titular;
	
	@OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
	private List<Biometria> biometrias;
	
	@OneToOne(cascade = CascadeType.MERGE) @JoinColumn(name = "bloqueio")
    private Bloqueio bloqueio;
	
	@Enumerated(value = EnumType.STRING)
    private StatusCartao status = StatusCartao.ATIVO;
	
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
	
	public boolean bloqueado(){
        return this.status.equals(StatusCartao.BLOQUEADO);
    }

    public void setBloqueio(Bloqueio bloqueio) {
        this.bloqueio = bloqueio;
        this.status = StatusCartao.BLOQUEADO;
    }

    public Bloqueio getBloqueio() {
        return bloqueio;
    }
}
