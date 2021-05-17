package br.com.zupacademy.brunoweberty.propostazup.biometria;

import java.net.URI;
import java.util.Optional;

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
import br.com.zupacademy.brunoweberty.propostazup.utils.ExecutorTransacao;

@RestController
@RequestMapping(value = "/v1/biometrias")
public class BiometriaController {
	
	@Autowired
	ExecutorTransacao transacao;
	
	@Autowired
	CartaoRepository cartaoRepository;
	
	@PostMapping("/{id}")
	public ResponseEntity<?> cadastrar(@PathVariable Long id, @RequestBody @Valid BiometriaRequest request, UriComponentsBuilder uriBuilder) {
		Optional<Cartao> possivelCartao = cartaoRepository.findById(id);
		
		if(possivelCartao.isPresent()) {
			Biometria biometria = request.converterEmBiometria(possivelCartao.get(), request.getBiometria());
			transacao.salvaEComita(biometria);
			URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(biometria.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
		
		if (possivelCartao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.badRequest().build();
	}
}
