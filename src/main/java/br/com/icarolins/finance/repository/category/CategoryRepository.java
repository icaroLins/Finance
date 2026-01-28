package br.com.icarolins.finance.repository.category;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.icarolins.finance.model.category.CategoryFinance;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryFinance, Long>{
    boolean existsByName(String name);

    Optional<CategoryFinance> findByName(String name);

    List<CategoryFinance> findByUserIsNullOrUserId(Long userId);
}
