package br.com.icarolins.finance.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import br.com.icarolins.finance.model.category.CategoryFinance;
import br.com.icarolins.finance.repository.category.CategoryRepository;

@Component
@DependsOn("entityManagerFactory")
public class CategoryInitializer implements CommandLineRunner{

    
    private final CategoryRepository categoryRepository; 

    public CategoryInitializer(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    private void createIfNotExists(String name){
        if(!categoryRepository.existsByName(name)){
            categoryRepository.save(new CategoryFinance(name));
        }
    }

    @Override
    public void run(String... args) throws Exception {
        createIfNotExists("Alimentação");
        createIfNotExists("Tranporte");
        createIfNotExists("Moradia");
        createIfNotExists("Lazer");
        createIfNotExists("Saúde");
        createIfNotExists("Salário");
    }

}
