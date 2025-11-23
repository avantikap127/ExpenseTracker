/*PS D:\Amy folder\AProjects\ExpenseTracker(java)> javac -cp "lib/gson-2.10.1.jar" -d out src\ExpenseTrackerApp.java src\model\Expense.java src\service\FileService.java src\service\ExpenseService.java
PS D:\Amy folder\AProjects\ExpenseTracker(java)> java -cp "out;lib/gson-2.10.1.jar" ExpenseTrackerApp
 */
import java.util.List;
import java.util.Scanner;
import model.Expense;
import service.ExpenseService;

public class ExpenseTrackerApp {
    private static ExpenseService expenseService = new ExpenseService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = readInt("Enter your choice: ");
            handleChoice(choice);
        } while (choice != 6);

        System.out.println("Thank you for using Expense Tracker!");
    }

    private static void showMenu() {
        System.out.println("\n=== Expense Tracker Menu ===");
        System.out.println("1. Add Expense");
        System.out.println("2. View All Expenses");
        System.out.println("3. View Expenses by Category");
        System.out.println("4. Delete Expense");
        System.out.println("5. View Total Expenses");
        System.out.println("6. Exit");
    }

    private static void handleChoice(int choice) {
        switch (choice) {
            case 1:
                addExpense();
                break;
            case 2:
                viewAllExpenses();
                break;
            case 3:
                viewExpensesByCategory();
                break;
            case 4:
                deleteExpense();
                break;
            case 5:
                viewTotalExpenses();
                break;
            case 6:
                break;
            default:
                System.out.println("Invalid choice! Please try again.");
        }
    }

    private static void addExpense() {
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        double amount = readDouble("Enter amount: ");
        System.out.print("Enter category (Food, Travel, Shopping, Bills, Medical, Other): ");
        String category = scanner.nextLine();
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        Expense expense = new Expense(description, amount, category, date);
        expenseService.addExpense(expense);
        System.out.println("Expense added successfully!");
    }

    private static void viewAllExpenses() {
        List<Expense> expenses = expenseService.getAllExpenses();
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded yet.");
        } else {
            System.out.println("\n--- All Expenses ---");
            for (int i = 0; i < expenses.size(); i++) {
                System.out.println((i + 1) + ". " + expenses.get(i));
            }
        }
    }

    private static void viewExpensesByCategory() {
        System.out.print("Enter category to filter: ");
        String category = scanner.nextLine();
        List<Expense> filtered = expenseService.getExpensesByCategory(category);
        if (filtered.isEmpty()) {
            System.out.println("No expenses found for category: " + category);
        } else {
            System.out.println("\n--- Expenses for Category: " + category + " ---");
            for (Expense expense : filtered) {
                System.out.println(expense);
            }
        }
    }

    private static void deleteExpense() {
        viewAllExpenses();
        int index = readInt("Enter the number of expense to delete: ") - 1;
        if (expenseService.deleteExpense(index)) {
            System.out.println("Expense deleted successfully!");
        } else {
            System.out.println("Invalid index! Could not delete expense.");
        }
    }

    private static void viewTotalExpenses() {
        double total = expenseService.getTotalExpenses();
        System.out.println("Total Expenses: $" + total);
    }

    // Utility methods
    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Try again.");
            }
        }
    }
}
