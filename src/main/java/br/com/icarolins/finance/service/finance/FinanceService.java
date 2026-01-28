package br.com.icarolins.finance.service.finance;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.icarolins.finance.model.category.CategoryFinance;
import br.com.icarolins.finance.model.finance.Finance;
import br.com.icarolins.finance.repository.category.CategoryRepository;
import br.com.icarolins.finance.repository.finance.FinanceRepository;

@Service
public class FinanceService {

    @Autowired
    private FinanceRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Finance createFinance(Finance finance, Long categoryId) {
        CategoryFinance category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        finance.setCategory(category);
        return repository.save(finance);
    }

    public List<Finance> listFinance(Long userId) {
        return repository.findByUser_id(userId);
    }

    public Finance editFinance(Finance atualizada, Long userId, Long financeId) {
        Finance finance = repository.findById(financeId)
                .orElseThrow(() -> new RuntimeException("Lançamento Financeiro não encontradaS"));

        if (!finance.getUser().getId().equals(userId)) {
            throw new RuntimeException("Você não pode alterar o status dessa atividade!");
        }

        finance.setValue(atualizada.getValue());
        finance.setType(atualizada.getType());

        return repository.save(finance);
    }

    public void deleteFinance(Long userId, Long financeId) {
        Finance finance = repository.findById(financeId)
                .orElseThrow(() -> new RuntimeException("Lançamento Financeiro não encontradaS"));

        if (!finance.getUser().getId().equals(userId)) {
            throw new RuntimeException("Você não pode alterar o status dessa atividade!");
        }

        repository.deleteById(financeId);
    }

    public List<Finance> listarPorMesAno(Long userId, int mes, int ano){
        LocalDate start = LocalDate.of(ano, mes, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        
        return repository.findByUserIdAndDateBetween(userId, start, end);
    }
}
