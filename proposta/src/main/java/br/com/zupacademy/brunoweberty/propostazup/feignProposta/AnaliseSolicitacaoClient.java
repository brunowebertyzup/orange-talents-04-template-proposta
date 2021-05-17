package br.com.zupacademy.brunoweberty.propostazup.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "proposta", url = "http://localhost:9999")
@Component
public interface AnaliseSolicitacaoClient {

	@PostMapping("/api/solicitacao")
	AnaliseSolicitacaoResponse postProposta(AnaliseSolicitacaoRequest postRequest);

}
