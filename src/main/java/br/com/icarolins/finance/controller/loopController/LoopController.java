package br.com.icarolins.finance.controller.loopController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.icarolins.finance.model.User;
import br.com.icarolins.finance.model.category.CategoryFinance;
import br.com.icarolins.finance.model.financeLoop.FinanceRecorrente;
import br.com.icarolins.finance.repository.category.CategoryRepository;
import br.com.icarolins.finance.repository.loopRepository.LoopRepository;
import br.com.icarolins.finance.service.UserService;

@RestController
@RequestMapping("/loop")
public class LoopController {

    @Autowired
    private final UserService userService;
    @Autowired
    private final LoopRepository loopRepository;
    @Autowired
    private final CategoryRepository categoryRepository;

    public LoopController(UserService userService, LoopRepository loopRepository,
            CategoryRepository categoryRepository) {
        this.userService = userService;
        this.loopRepository = loopRepository;
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/create/loop/{categoryId}")
    public ResponseEntity<?> creatLoopLance(Authentication authentication,
            @RequestBody FinanceRecorrente financeLoop,
            @PathVariable Long categoryId) {
        String email = authentication.getName();
        User user = userService.searchByEmail(email);

        financeLoop.setUser(user);

        financeLoop.setAtivo(true);

        CategoryFinance category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        financeLoop.setCategory(category);

        FinanceRecorrente saved = loopRepository.save(financeLoop);

        return ResponseEntity.ok(saved);
    }
}
