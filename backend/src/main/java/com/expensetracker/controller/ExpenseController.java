package com.expensetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.expensetracker.model.DashboardSummary;
import com.expensetracker.model.Expense;
import com.expensetracker.model.User;
import com.expensetracker.repository.UserRepository;
import com.expensetracker.security.JwtUtil;
import com.expensetracker.service.ExpenseService;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "https://expense-tracker-wheat-theta.vercel.app")
public class ExpenseController {

    @Autowired
    private ExpenseService service;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // ADD EXPENSE (user specific)
    @PostMapping
    public Expense addExpense(@RequestBody Expense expense,
                              @RequestHeader("Authorization") String token) {

        String email = jwtUtil.extractEmail(token.substring(7));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        expense.setUser(user);

        return service.addExpense(expense, email);
    }

    // GET ALL EXPENSES (user specific)
    @GetMapping
    public List<Expense> getAll(@RequestHeader("Authorization") String token) {

        String email = jwtUtil.extractEmail(token.substring(7));

        return service.getUserExpenses(email);
    }

    // GET EXPENSES BY CATEGORY (user specific)
    @GetMapping("/category/{id}")
    public List<Expense> byCategory(@PathVariable Long id,
                                    @RequestHeader("Authorization") String token) {

        String email = jwtUtil.extractEmail(token.substring(7));

        return service.getUserExpensesByCategory(email, id);
    }

    // DELETE EXPENSE (user specific)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id,
                                         @RequestHeader("Authorization") String token) {

        String email = jwtUtil.extractEmail(token.substring(7));

        service.deleteUserExpense(id, email);

        return ResponseEntity.ok("Expense deleted successfully");
    }

    // UPDATE EXPENSE (user specific)
    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id,
                                 @RequestBody Expense updatedExpense,
                                 @RequestHeader("Authorization") String token) {

        String email = jwtUtil.extractEmail(token.substring(7));

        return service.updateUserExpense(id, updatedExpense, email);
    }

    // TOTAL EXPENSE (user specific)
    @GetMapping("/total")
    public double total(@RequestHeader("Authorization") String token) {

        String email = jwtUtil.extractEmail(token.substring(7));

        return service.getUserTotalExpenses(email);
    }

    // DASHBOARD SUMMARY (user specific)
    @GetMapping("/dashboard")
    public DashboardSummary dashboard(@RequestHeader("Authorization") String token) {

        String email = jwtUtil.extractEmail(token.substring(7));

        return service.getUserDashboardSummary(email);
    }
}
