package br.com.zupacademy.brunoweberty.propostazup.carteiraPaypal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarteiraRepository extends JpaRepository<Carteira, Long>{
	
	@Query("SELECT c FROM Carteira c WHERE c.cartao.id = :id")
	List<Carteira> carregarPorIdDoCartao(@Param("id") Long id);
	
}
