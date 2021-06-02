package br.com.zupacademy.brunoweberty.propostazup.feignCartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zupacademy.brunoweberty.propostazup.bloqueio.BloqueioFeignRequest;
import br.com.zupacademy.brunoweberty.propostazup.bloqueio.BloqueioFeignResponse;
import br.com.zupacademy.brunoweberty.propostazup.carteiraPaypal.CarteiraRequest;
import br.com.zupacademy.brunoweberty.propostazup.carteiraPaypal.ResultadoCarteira;
import br.com.zupacademy.brunoweberty.propostazup.viagem.AvisoViagemRequest;
import br.com.zupacademy.brunoweberty.propostazup.viagem.ResultadoAvisoViagem;

@FeignClient(name = "cartoes", url = "http://localhost:8888/api/cartoes")
@Component
public interface CartaoClient {
	
	@RequestMapping(method = RequestMethod.GET,  produces = "application/json")
    public CartaoResponse getCartao(@RequestParam("idProposta") Long idProposta);
	
	@RequestMapping(method = RequestMethod.POST, value = "{id}/bloqueios", produces = "application/json")
    public BloqueioFeignResponse bloquearCartao(@PathVariable("id") String numeroCartao, @RequestBody BloqueioFeignRequest request);
	
	@RequestMapping(method = RequestMethod.POST, value = "{id}/avisos", produces = "application/json")
    public ResultadoAvisoViagem emitirAviso(@PathVariable("id") String numeroCartao, @RequestBody AvisoViagemRequest request);
	
	@RequestMapping(method = RequestMethod.POST, value = "{id}/carteiras", produces = "application/json")
    public ResultadoCarteira associarCarteira(@PathVariable("id") String numeroCartao, @RequestBody CarteiraRequest request);
}

