package br.com.icarolins.finance.service.category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.icarolins.finance.model.category.CategoryFinance;
import br.com.icarolins.finance.repository.category.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Optional<CategoryFinance> findByCategory(Long id) {
        return categoryRepository.findById(id);
    }

    public CategoryFinance createCategory(CategoryFinance finance) {
        return categoryRepository.save(finance);
    }

    public List<CategoryFinance> listCategory() {
        return categoryRepository.findAll();
    }

    public List<CategoryFinance> listCategoryUser(Long UserId) {
        return categoryRepository.findByUserIsNullOrUserId(UserId);
    }

    public CategoryFinance editCategory(CategoryFinance newCategory, Long userId, Long categoryId) {
        CategoryFinance categoryFinance = categoryRepository
        .findById(categoryId).orElseThrow(()->new RuntimeException("categoria não encontrada"));

        if(!categoryFinance.getUser().getId().equals(userId)){
            throw new RuntimeException("Você não pode alterar o status dessa categoria!");
        }
        categoryFinance.setName(newCategory.getName());
        categoryFinance.setDescription(newCategory.getDescription());

        return categoryRepository.save(categoryFinance);
    }

    public void deleteCategory(Long userId, Long categoryId){
        CategoryFinance categoryFinance = categoryRepository
        .findById(categoryId).orElseThrow(()->new RuntimeException("categoria não encontrada"));

        if(!categoryFinance.getUser().getId().equals(userId)){
            throw new RuntimeException("Você não pode alterar o status dessa categoria!");
        }

        categoryRepository.deleteById(categoryId);
    }
}
