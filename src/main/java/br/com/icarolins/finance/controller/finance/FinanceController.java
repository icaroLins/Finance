package br.com.icarolins.finance.controller.finance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.icarolins.finance.dto.finance.FinanceRequestDTO;
import br.com.icarolins.finance.model.User;
import br.com.icarolins.finance.model.finance.Finance;
import br.com.icarolins.finance.service.UserService;
import br.com.icarolins.finance.service.finance.FinanceService;

@RestController
@RequestMapping("/Finace")
public class FinanceController {
    @Autowired
    private final FinanceService financeService;
    @Autowired
    private final UserService userService;

    public FinanceController(FinanceService financeService, UserService userService) {
        this.financeService = financeService;
        this.userService = userService;
    }

    @PostMapping("/create/{categoryId}")
    public ResponseEntity<?> createFinance(Authentication authentication,
            @PathVariable Long categoryId,
            @RequestBody Finance finance) {
        String email = authentication.getName();
        User user = userService.searchByEmail(email);

        finance.setUser(user);

        Finance newFinance = financeService.createFinance(finance, categoryId);

        return ResponseEntity.ok(newFinance);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listFinance(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.searchByEmail(email);

        List<Finance> list = financeService.listFinance(user.getId());

        return ResponseEntity.ok(list);
    }

    @PutMapping("/edit/{financeId}")
    public ResponseEntity<?> editFinance(Authentication authentication,
            @PathVariable Long financeId,
            @RequestBody Finance atualizada) {
        String email = authentication.getName();
        User user = userService.searchByEmail(email);

        Finance edit = financeService.editFinance(atualizada, user.getId(), financeId);

        return ResponseEntity.ok(edit);
    }

    @DeleteMapping("/delete/{financeId}")
    public ResponseEntity<Void> deleteFinance(Authentication authentication,
            @PathVariable Long financeId) {
        String email = authentication.getName();
        User user = userService.searchByEmail(email);

        financeService.deleteFinance(user.getId(), financeId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list/data")
    public ResponseEntity<?> listFinanceMesAno(Authentication authentication,
            @RequestParam int mes,
            @RequestParam int ano) {

        String email = authentication.getName();
        User user = userService.searchByEmail(email);

        List<Finance> finance = financeService.listarPorMesAno(user.getId(), mes, ano);

        return ResponseEntity.ok(finance);
    }

}
