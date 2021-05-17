package br.com.zupacademy.brunoweberty.propostazup.cartao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zupacademy.brunoweberty.propostazup.feign.AnaliseSolicitacaoRequest;
import br.com.zupacademy.brunoweberty.propostazup.proposta.Proposta;
import br.com.zupacademy.brunoweberty.propostazup.proposta.PropostaRepository;
import br.com.zupacademy.brunoweberty.propostazup.utils.ExecutorTransacao;
import feign.FeignException;

@Component
public class CartaoScheduler {
	
	@Autowired
	CartaoClient cartaoCliente;
	
	@Autowired
	PropostaRepository propostaRepository;
	
	@Autowired
	ExecutorTransacao transacao;
	
	@Scheduled(fixedRateString = "100000")
	public void verificaSituacaoDoCartao() {
		
		var propostasAprovadas = propostaRepository.buscarPropostasElegiveisSemCartao();
		
		for (Proposta proposta : propostasAprovadas) {
			AnaliseSolicitacaoRequest postRequest = proposta.converterEmPostRequest();
			try {
				CartaoResponse cartao = cartaoCliente.postCartao(postRequest);
				proposta.setNumeroCartao(cartao.getId());
				transacao.atualizaEComita(proposta);
			} catch (FeignException e) {
				// TODO: handle exception
			}
		}
		
	}
}
