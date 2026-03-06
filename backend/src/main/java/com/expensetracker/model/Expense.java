package com.expensetracker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "expenses")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private double amount;
    // MANY expenses → ONE category
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Category category;

    private String date;

    @ManyToOne
    @JoinColumn(name = "type_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Type type; // INCOME or EXPENSE


    public Expense() {}

    public Expense(String description, double amount, Category category, String date, Type type) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.type = type;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public Type getType() {
    return type;
   }

   public void setType(Type type) {
    this.type = type;
}

}
