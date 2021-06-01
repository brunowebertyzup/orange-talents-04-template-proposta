package br.com.zupacademy.brunoweberty.propostazup.carteiraPaypal;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.brunoweberty.propostazup.cartao.Cartao;
import br.com.zupacademy.brunoweberty.propostazup.cartao.CartaoRepository;
import br.com.zupacademy.brunoweberty.propostazup.feignCartao.CartaoClient;
import br.com.zupacademy.brunoweberty.propostazup.feignCartao.CartaoFeignHandler;
import br.com.zupacademy.brunoweberty.propostazup.utils.ExecutorTransacao;

@RestController
@RequestMapping(value = "/v1/carteiras")
public class CarteiraController {
	
	@Autowired CartaoRepository cartaoRepository;
    @Autowired ExecutorTransacao transacao;
	@Autowired CartaoClient cartaoClient;
	@Autowired CartaoFeignHandler cartaoFeignHandler;
	@Autowired CarteiraRepository carteiraRepository;
	
	@PostMapping("/{idCartao}")
	@Transactional
	public ResponseEntity<?> cadastrar(@PathVariable("idCartao") Long idCartao, 
														@RequestBody @Valid CarteiraRequest request,
														UriComponentsBuilder uriBuilder) {
		Optional<Cartao> possivelCartao = cartaoRepository.findById(idCartao);
		if(possivelCartao.isPresent()) {
			List<Carteira> carteiras = carteiraRepository.carregarPorIdDoCartao(possivelCartao.get().getId());
			if(!carteiras.isEmpty()) {
				return ResponseEntity.unprocessableEntity().build();
			}
		}
		
		return possivelCartao.map(cartaoEncontrado -> {
			Carteira carteira = request.toModel(cartaoEncontrado);
			cartaoFeignHandler.executa(() -> {
				cartaoClient.associarCarteira(cartaoEncontrado.getNumeroCartao(), request);
					transacao.salvaEComita(carteira);
					return null;
            }, "Já existe um cartão associado a essa carteira");
			
			URI uri = uriBuilder.path("/viagens/{id}").buildAndExpand(carteira.getId()).toUri();
			return ResponseEntity.created(uri).build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
}
