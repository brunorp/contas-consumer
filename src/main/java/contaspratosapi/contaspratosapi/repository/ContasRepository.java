package contaspratosapi.contaspratosapi.repository;

import contaspratosapi.contaspratosapi.model.Conta;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ContasRepository extends JpaRepository<Conta, Integer> {
    @Query("SELECT c FROM Conta c ORDER BY data DESC")
    Conta findByHighestDate(PageRequest pageable);
}
