package br.com.zupacademy.brunoweberty.propostazup.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.zupacademy.brunoweberty.propostazup.feign.PostRequest;

@FeignClient(name = "cartao", url = "http://localhost:8888/api/cartoes")
@Component
public interface CartaoClient {

	@RequestMapping(method = RequestMethod.POST)
	CartaoResponse postCartao(PostRequest proposta);

}

