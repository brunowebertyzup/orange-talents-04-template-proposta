package br.com.zupacademy.brunoweberty.propostazup.proposta;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long>{

	Optional<Proposta> findByDocumento(String documento);
	
	@Query("SELECT p FROM Proposta p WHERE status_proposta = 0 and p.cartao IS NULL")
	List<Proposta> buscarPropostasElegiveisSemCartao();
}
