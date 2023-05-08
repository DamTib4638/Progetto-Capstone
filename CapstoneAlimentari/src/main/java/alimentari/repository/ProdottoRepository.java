package alimentari.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import alimentari.entity.CategoriaProdotto;
import alimentari.entity.Prodotto;
import alimentari.entity.TipoProdotto;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
	
	Optional<Prodotto> findByNome(String nome);
	
	Boolean existsByNome(String nome);
	
	List<Prodotto> findByQtaDisponibileOrPesoDisponibileIsGreaterThanAndDataScadenzaIsLessThan(Integer qtaZero, Double pesoZero, Date dataScadenza);
	
	List<Prodotto> findByCategoriaProdotto(CategoriaProdotto categoriaProdotto);
	
	List<Prodotto> findByTipoProdotto(TipoProdotto tipoProdotto);
	
	List<Prodotto> findByPercentualeOffertaIsNotNull();
	
	List<Prodotto> findAllByOrderByIdProdotto();

}
