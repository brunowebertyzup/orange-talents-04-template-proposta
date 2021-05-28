package br.com.zupacademy.brunoweberty.propostazup.viagem;

import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.brunoweberty.propostazup.cartao.Cartao;
import br.com.zupacademy.brunoweberty.propostazup.cartao.CartaoRepository;
import br.com.zupacademy.brunoweberty.propostazup.utils.ExecutorTransacao;

@RestController
@RequestMapping(value = "/v1/viagens")
public class AvisoViagemController {
	
	@Autowired CartaoRepository cartaoRepository;
    @Autowired ExecutorTransacao transacao;
	
	@PostMapping("/{idCartao}")
	@Transactional
	public ResponseEntity<?> cadastrar(@PathVariable("idCartao") Long idCartao, 
														@RequestBody @Valid AvisoViagemRequest request,
														HttpServletRequest servletRequest,
														@RequestHeader(value = "User-Agent") String userAgent, 
														UriComponentsBuilder uriBuilder) {
		Optional<Cartao> possivelCartao = cartaoRepository.findById(idCartao);
		return possivelCartao.map(cartaoEncontrado -> {
			AvisoViagem avisoViagem = request.toModel(cartaoEncontrado, servletRequest.getRemoteAddr(), userAgent);
			transacao.salvaEComita(avisoViagem);
			URI uri = uriBuilder.path("/viagens/{id}").buildAndExpand(avisoViagem.getId()).toUri();
            return ResponseEntity.ok(uri);
        }).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
}
