package com.expensetracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expensetracker.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByCategory_CategoryId(Long categoryId); // Basically categoryById is a derived query(method) and it does not exist in jpa repository so we are creating this. We are declaring the method in the repository interface.Spring automatically generates its implementation at runtime.
    /* What is derived query? A Derived Query is not the SQL itself, but the query method whose SQL is automatically derived (generated) from the method name. You write the method name → Spring reads the name → Spring automatically generates the SQL query. So you just need to declare the method in the repository interface, and Spring will take care of generating the SQL query based on the method name. For example, if you have a method named findByCategory_CategoryId, Spring will understand that you want to find expenses by category ID and will generate the appropriate SQL query to retrieve the data from the database.
    You write the method name → Spring reads the name → Spring automatically generates the SQL query. */
    List<Expense> findByUser(User user);
}
