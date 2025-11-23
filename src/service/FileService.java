package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Expense;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    private static final String FILE_PATH = "data/expenses.json";
    private Gson gson = new Gson();

    // Load expenses from JSON file
    public List<Expense> loadExpenses() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            FileReader reader = new FileReader(file);
            Type listType = new TypeToken<List<Expense>>() {}.getType();
            List<Expense> expenses = gson.fromJson(reader, listType);
            reader.close();
            return expenses != null ? expenses : new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Error loading expenses: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Save expenses to JSON file
    public void saveExpenses(List<Expense> expenses) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            gson.toJson(expenses, writer);
            writer.close();
        } catch (Exception e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }
}
