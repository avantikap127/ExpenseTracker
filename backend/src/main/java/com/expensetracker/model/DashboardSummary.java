package com.expensetracker.model;

public class DashboardSummary {

    private double totalIncome;
    private double totalExpense;
    private double balance;

    public DashboardSummary(double income,
                            double expense,
                            double balance) {
        this.totalIncome = income;
        this.totalExpense = expense;
        this.balance = balance;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public double getBalance() {
        return balance;
    }
}
