package br.com.zupacademy.brunoweberty.propostazup.proposta;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.brunoweberty.propostazup.feign.PostClient;
import br.com.zupacademy.brunoweberty.propostazup.feign.PostRequest;
import br.com.zupacademy.brunoweberty.propostazup.feign.PostResponse;
import br.com.zupacademy.brunoweberty.propostazup.feign.ResultadoSolicitacao;
import br.com.zupacademy.brunoweberty.propostazup.utils.ExecutorTransacao;
import feign.FeignException.UnprocessableEntity;

@RestController
@RequestMapping(value = "/v1/propostas")
public class PropostaController {
	
	@Autowired
	PropostaRepository propostaRepository;
	
	@Autowired
	PostClient verificarProposta;
	
	@Autowired
	ExecutorTransacao transacao;
	
	@PostMapping
	@Transactional
	public ResponseEntity<PropostaRequest> cadastrar(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder uriBuilder) {
		Proposta proposta = request.converterEmProposta();
		Optional<Proposta> possivelProposta = propostaRepository
				.findByDocumento(request.getDocumento());
		if(possivelProposta.isPresent()) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
		propostaRepository.save(proposta);
		analisaProposta(proposta);
		URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
		return ResponseEntity.created(uri).body(new PropostaRequest(proposta));
	}
	
	private void analisaProposta(Proposta proposta) {
		PostRequest postRequest = proposta.converterEmPostRequest();
		try {
			PostResponse post = verificarProposta.postProposta(postRequest);
			if(post.getResultadoSolicitacao().equals(ResultadoSolicitacao.SEM_RESTRICAO)) {
				proposta.setStatusProposta(StatusProposta.ELEGIVEL);
				transacao.atualizaEComita(proposta);
			}
			
		} catch (UnprocessableEntity e) {
			proposta.setStatusProposta(StatusProposta.NAO_ELEGIVEL);
			transacao.atualizaEComita(proposta);
		}
	}
	
}
