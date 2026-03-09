package br.com.icarolins.finance.repository.loopRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.icarolins.finance.model.financeLoop.FinanceRecorrente;

@Repository
public interface LoopRepository extends JpaRepository<FinanceRecorrente, Long> {
    List<FinanceRecorrente> findByAtivoTrue();
}
