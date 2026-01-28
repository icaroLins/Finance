package br.com.icarolins.finance.controller.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.icarolins.finance.model.User;
import br.com.icarolins.finance.model.category.CategoryFinance;
import br.com.icarolins.finance.service.UserService;
import br.com.icarolins.finance.service.category.CategoryService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(Authentication authentication,
            @RequestBody CategoryFinance categoryFinance) {
        String email = authentication.getName();
        User user = userService.searchByEmail(email);

        categoryFinance.setUser(user);

        CategoryFinance category = service.createCategory(categoryFinance);

        return ResponseEntity.ok(category);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listCategory(Authentication authentication){
        String email = authentication.getName();
        User user = userService.searchByEmail(email);

        List<CategoryFinance> list = service.listCategoryUser(user.getId());

        return ResponseEntity.ok(list);
    }
}
