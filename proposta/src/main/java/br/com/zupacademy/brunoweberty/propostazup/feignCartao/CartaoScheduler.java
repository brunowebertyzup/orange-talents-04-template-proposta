package br.com.zupacademy.brunoweberty.propostazup.feignCartao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zupacademy.brunoweberty.propostazup.cartao.Cartao;
import br.com.zupacademy.brunoweberty.propostazup.cartao.CartaoRepository;
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
	CartaoRepository cartaoRepository;
	
	@Autowired
	ExecutorTransacao transacao;
	
	@Scheduled(fixedRateString = "10000")
	public void verificaSituacaoDoCartao() {
		
		var propostasAprovadas = propostaRepository.buscarPropostasElegiveisSemCartao();
		
		for (Proposta proposta : propostasAprovadas) {
			try {
				CartaoResponse cartaoResponse = cartaoCliente.getCartao(proposta.getId());
				Cartao cartaoSalvar = cartaoResponse.converterEmCartao();
				transacao.salvaEComita(cartaoSalvar);
				Cartao cartao = cartaoRepository.getOne(cartaoSalvar.getId());
				proposta.setCartao(cartao);
				transacao.atualizaEComita(proposta);
			} catch (FeignException e) {
				// TODO: handle exception
			}
		}
		
	}
}
