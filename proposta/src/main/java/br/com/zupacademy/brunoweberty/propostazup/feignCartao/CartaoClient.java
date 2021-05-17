package br.com.zupacademy.brunoweberty.propostazup.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.zupacademy.brunoweberty.propostazup.feign.AnaliseSolicitacaoRequest;

@FeignClient(name = "cartao", url = "http://localhost:8888")
@Component
public interface CartaoClient {

	@PostMapping("/api/cartoes")
	CartaoResponse postCartao(AnaliseSolicitacaoRequest proposta);

}

