package br.com.zupacademy.brunoweberty.propostazup.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "post", url = "http://localhost:9999/api/solicitacao")
@Component
public interface PostClient {

	@RequestMapping(method = RequestMethod.POST)
	PostResponse postProposta(PostRequest postRequest);

}
