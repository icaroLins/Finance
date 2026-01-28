package br.com.icarolins.finance.model.finance;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.icarolins.finance.dto.TypeValue;
import br.com.icarolins.finance.model.User;
import br.com.icarolins.finance.model.category.CategoryFinance;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "finance_tb")
public class Finance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    private TypeValue type;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private CategoryFinance category;

   

    public TypeValue getType() {
        return type;
    }

    public void setType(TypeValue type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public CategoryFinance getCategory() {
        return category;
    }

    public void setCategory(CategoryFinance category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    

}
