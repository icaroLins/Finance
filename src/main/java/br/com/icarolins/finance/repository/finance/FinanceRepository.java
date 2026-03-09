package br.com.icarolins.finance.repository.finance;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.icarolins.finance.dto.TypeValue;
import br.com.icarolins.finance.model.User;
import br.com.icarolins.finance.model.category.CategoryFinance;
import br.com.icarolins.finance.model.finance.Finance;

@Repository
public interface FinanceRepository extends JpaRepository<Finance, Long>{
    List<Finance> findByUser_id(Long userId);

    List<Finance> findByCategory_id(Long categoryId);

    boolean existsByUserAndCategoryAndDate(User user, CategoryFinance category, LocalDate date);

    List<Finance> findByUserIdAndType(Long userId, TypeValue type);

    List<Finance> findByUserIdAndDateBetween(
        Long UserId,
        LocalDate start,
        LocalDate end
    );
}
