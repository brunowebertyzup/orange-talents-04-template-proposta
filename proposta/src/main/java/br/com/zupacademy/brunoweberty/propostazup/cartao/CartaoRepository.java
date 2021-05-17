package br.com.zupacademy.brunoweberty.propostazup.cartao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long>{

	Cartao findByNumeroCartao(String numeroCartao);

}
