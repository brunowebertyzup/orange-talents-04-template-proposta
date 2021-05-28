package br.com.zupacademy.brunoweberty.propostazup.bloqueio;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.brunoweberty.propostazup.cartao.Cartao;
import br.com.zupacademy.brunoweberty.propostazup.cartao.CartaoRepository;
import br.com.zupacademy.brunoweberty.propostazup.feignCartao.CartaoClient;
import br.com.zupacademy.brunoweberty.propostazup.feignCartao.CartaoFeignHandler;
import br.com.zupacademy.brunoweberty.propostazup.utils.ExecutorTransacao;

@RestController
@RequestMapping(value = "/v1/bloqueios")
public class BloqueioController {
	
	@Autowired CartaoRepository cartaoRepository;
    @Autowired ExecutorTransacao transacao;
    @Autowired CartaoClient cartaoClient;
    @Autowired CartaoFeignHandler cartaoFeignHandler;
	
	@PostMapping("/{idCartao}")
    public ResponseEntity<?> novoBloqueio(@PathVariable("idCartao") Long idCartao,
                                          HttpServletRequest servletRequest,
                                          @RequestHeader(value = "User-Agent") String userAgent){
        Optional<Cartao> possivelCartao = cartaoRepository.findById(idCartao);

        return possivelCartao.map(cartaoEncontrado -> {
            if(cartaoEncontrado.bloqueado()){
                return ResponseEntity.unprocessableEntity().body("Cartão já bloqueado.");
            }
            Bloqueio novoBloqueio = new Bloqueio(servletRequest.getRemoteAddr(), userAgent, cartaoEncontrado);
            bloqueiaCartao(novoBloqueio, cartaoEncontrado);
            return ResponseEntity.ok().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
	
	private void bloqueiaCartao(Bloqueio novoBloqueio, Cartao cartaoEncontrado){
        cartaoFeignHandler.executa(() -> {
            BloqueioFeignResponse bloqueioFeignResponse = cartaoClient.bloquearCartao(
                    cartaoEncontrado.getNumeroCartao(), new BloqueioFeignRequest());
            Assert.isTrue(bloqueioFeignResponse.bloqueado(), "O resultado deveria ser BLOQUEADO");
            cartaoEncontrado.setBloqueio(novoBloqueio);
            transacao.atualizaEComita(cartaoEncontrado);
            return null;
        }, "Cartão já se encontra bloqueado no serviço externo.");
    }
	
}
