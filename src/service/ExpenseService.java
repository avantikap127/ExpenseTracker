//Abstracrion
package service;

import java.util.ArrayList;
import java.util.List;
import model.Expense;

public class ExpenseService {
    private FileService fileService = new FileService();
    private List<Expense> expenses = new ArrayList<>();

    public ExpenseService() {
        expenses = fileService.loadExpenses();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        fileService.saveExpenses(expenses);
    }

    // ✅ Add these three methods below
    public List<Expense> getAllExpenses() {
        return expenses;
    }

    public List<Expense> getExpensesByCategory(String category) {
        List<Expense> filtered = new ArrayList<>();
        for (Expense expense : expenses) {
            if (expense.getCategory().equalsIgnoreCase(category)) {
                filtered.add(expense);
            }
        }
        return filtered;
    }

    public boolean deleteExpense(int index) {
        if (index >= 0 && index < expenses.size()) {
            expenses.remove(index);
            fileService.saveExpenses(expenses);
            return true;
        }
        return false;
    }

    public double getTotalExpenses() {
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        return total;
    }
}
