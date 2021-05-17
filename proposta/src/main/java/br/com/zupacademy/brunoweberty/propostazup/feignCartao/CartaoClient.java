package br.com.zupacademy.brunoweberty.propostazup.feignCartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cartao", url = "http://localhost:8888")
@Component
public interface CartaoClient {

	@GetMapping("/api/cartoes?idProposta={idProposta}")
	CartaoResponse getCartao(@PathVariable Long idProposta);

}

