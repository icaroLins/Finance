package br.com.icarolins.finance.dto.finance;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.icarolins.finance.dto.TypeValue;


public class FinanceRequestDTO {
    private BigDecimal value;
    private LocalDate date;
    private TypeValue type;

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public TypeValue getType() {
        return type;
    }
    public void setType(TypeValue type) {
        this.type = type;
    }
    public BigDecimal getValue() {
        return value;
    }
    public void setValue(BigDecimal value) {
        this.value = value;
    }
    

    
}
