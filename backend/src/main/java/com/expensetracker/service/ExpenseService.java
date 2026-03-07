package com.expensetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expensetracker.model.DashboardSummary;
import com.expensetracker.model.Expense;
import com.expensetracker.repository.ExpenseRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository repo;

    public Expense addExpense(Expense expense) {
        
    Long categoryId = expense.getCategory().getCategoryId();
    Long typeId = expense.getType().getTypeId();

    Category category = categoryRepository.findById(categoryId).orElse(null);
    Type type = typeRepository.findById(typeId).orElse(null);

    expense.setCategory(category);
    expense.setType(type);
    return repo.save(expense);      //save(), findAll(), deleteById() are built-in methods provided by JpaRepository interface.These methods are CRUD operations provided by Spring Data JPA through JpaRepository. 
                // The save() method is used to persist an entity (in this case, an Expense) to the database. It can be used for both creating new records and updating existing ones. When you call save() with an entity that has a null ID, it will create a new record in the database. If you call save() with an entity that has a non-null ID, it will update the existing record with that ID in the database. The findAll() method retrieves all records of the entity type from the database, returning them as a List. The deleteById() method deletes a record from the database based on its ID. These methods abstract away the underlying database operations, allowing you to perform CRUD operations without writing explicit SQL queries. 
        // Hibernate, as the JPA provider, converts these operations into SQL queries executed via JDBC.
    }   // so whatever sql queries hibernate creates when having all these methods, those sql queries are taken by jdbc to mysql  database so that jdbc driver executes those sql queries there and take the result and pass to hibernate, hibernate covert the result to entity object, then pass to repository then repository to service and then service to controller.  
          

    public List<Expense> getAllExpenses() {
        return repo.findAll(); }                               /*  Suppose you have a database table: id	amount	category
                                                                                                     1	  500	 Food
                                                                                                     2	  1000	 Travel
                                                                                                     @Entity
                                                                                                    public class Expense {

                                                                                                        @Id
                                                                                                        @GeneratedValue(strategy = GenerationType.IDENTITY)
                                                                                                        private Long id;

                                                                                                        private Double amount;

                                                                                                        private String category;

                                                                                                    }   This class is an Entity class.
                                                                                                    Now when you create:
                                                                                                    Expense e = new Expense();
                                                                                                    e.setAmount(500);
                                                                                                    e.setCategory("Food");
                                                                                                    That object e is called an:
                                                                                                    Entity Object
                                                                                                    Because it represents a row in the table.*/

    public List<Expense> getByCategory(Long categoryId) {
    return repo.findByCategory_CategoryId(categoryId);
}

    public void deleteExpense(Long id) {
        repo.deleteById(id);
    }

    public double getTotalExpenses() {
        return repo.findAll()
                .stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    public DashboardSummary getDashboardSummary() {

    List<Expense> list = repo.findAll();

    double income = list.stream()
        .filter(e -> "INCOME"
        .equalsIgnoreCase(e.getType().getTypeName()))
        .mapToDouble(Expense::getAmount)
        .sum();

   
    double expense = list.stream()
        .filter(e -> "EXPENSE"
        .equalsIgnoreCase(e.getType().getTypeName()))
        .mapToDouble(Expense::getAmount)
        .sum();
        
    double balance = income - expense;

    return new DashboardSummary(income, expense, balance);
}

public Expense updateExpense(Long id, Expense updatedExpense) {

    Expense existing = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Expense not found"));

    existing.setDescription(updatedExpense.getDescription());
    existing.setAmount(updatedExpense.getAmount());
    existing.setCategory(updatedExpense.getCategory());
    existing.setDate(updatedExpense.getDate());
    existing.setType(updatedExpense.getType());

    return repo.save(existing);
}


}
