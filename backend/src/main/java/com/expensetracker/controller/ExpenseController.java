package com.expensetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.model.DashboardSummary;
import com.expensetracker.model.Expense;
import com.expensetracker.service.ExpenseService;


@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController {

    @Autowired
    private ExpenseService service; // Spring injects ExpenseService object into Controller. service object is used to call methods defined in ExpenseService class.

    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        return service.addExpense(expense);
    }

    @GetMapping
    public List<Expense> getAll() {
        return service.getAllExpenses();
    }
    @GetMapping("/category/{id}")
    public List<Expense> byCategory(@PathVariable Long id) {
      return service.getByCategory(id);
 }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

    service.deleteExpense(id);

    return ResponseEntity.ok("Expense deleted successfully");
}

    @GetMapping("/total")
    public double total() {
        return service.getTotalExpenses();
    }
    @PutMapping("/{id}")
     public Expense updateExpense(@PathVariable Long id,
                             @RequestBody Expense updatedExpense) {
    return service.updateExpense(id, updatedExpense);
}

    @GetMapping("/dashboard")
    public DashboardSummary dashboard() {
        return service.getDashboardSummary();
}

}
