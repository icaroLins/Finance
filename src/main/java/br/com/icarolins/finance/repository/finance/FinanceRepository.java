package br.com.icarolins.finance.repository.finance;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.icarolins.finance.model.finance.Finance;

@Repository
public interface FinanceRepository extends JpaRepository<Finance, Long>{
    List<Finance> findByUser_id(Long userId);

    List<Finance> findByCategory_id(Long categoryId);

    List<Finance> findByUserIdAndDateBetween(
        Long UserId,
        LocalDate start,
        LocalDate end
    );
}
