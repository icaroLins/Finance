package br.com.icarolins.finance.service.loopService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.icarolins.finance.dto.TypeValue;
import br.com.icarolins.finance.model.User;
import br.com.icarolins.finance.model.finance.Finance;
import br.com.icarolins.finance.model.financeLoop.FinanceRecorrente;
import br.com.icarolins.finance.repository.UserRepository;
import br.com.icarolins.finance.repository.finance.FinanceRepository;
import br.com.icarolins.finance.repository.loopRepository.LoopRepository;

@Service
public class LoopService {
    @Autowired
    private LoopRepository loopRepository;

    @Autowired
    private FinanceRepository financeRepository;

    @Autowired
    private UserRepository userRepository;

    @Scheduled(cron = "0 10 0 * * *")
    public void gerarLancamentosMensais() {

        LocalDate hoje = LocalDate.now();
        int dia = hoje.getDayOfMonth();

        List<FinanceRecorrente> recorrente = loopRepository.findByAtivoTrue();

        for (FinanceRecorrente r : recorrente) {

            if (r.getDiaDoMes() == dia) {
                
                User user = r.getUser();

                boolean jaExiste = financeRepository.existsByUserAndCategoryAndDate(
                user,
                r.getCategory(),
                hoje
            );

            if (jaExiste) {
                continue; // evita duplicações
            }

                Finance l = new Finance();
                l.setValue(r.getValue());
                l.setDate(hoje);
                l.setType(r.getType());
                l.setCategory(r.getCategory());
                l.setUser(r.getUser());

                if (r.getType() == TypeValue.PROHIBITED) {
                    user.setValuePROHIBITED(user.getValuePROHIBITED().add(r.getValue()));
                } else {
                    user.setValueExit(user.getValueExit().add(r.getValue()));
                }

                user.setValueTotal(user.getValuePROHIBITED().subtract(user.getValueExit()));
                userRepository.save(user);

                financeRepository.save(l);

            }
        }
    }
}
