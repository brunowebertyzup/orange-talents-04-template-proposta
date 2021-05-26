package br.com.zupacademy.brunoweberty.propostazup.bloqueio;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import br.com.zupacademy.brunoweberty.propostazup.cartao.Cartao;

@Entity
public class Bloqueio {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime bloqueadoEm = LocalDateTime.now();
    @NotBlank private String ipSolicitante;
    @NotBlank private String agenteDoUsuario;
    @NotNull @OneToOne @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(@NotBlank String ipSolicitante, @NotBlank String agenteDoUsuario, @NotNull @Valid Cartao cartao) {
        Assert.hasLength(ipSolicitante, "IP do solicitante não deveria ser nulo");
        Assert.hasLength(agenteDoUsuario, "User Agent não deveria ser nulo");
        Assert.notNull(cartao, "Cartão não deveria ser nulo");
        this.ipSolicitante = ipSolicitante;
        this.agenteDoUsuario = agenteDoUsuario;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
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
