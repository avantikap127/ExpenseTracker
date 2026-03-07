package com.expensetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.expensetracker.model.Type;
import com.expensetracker.repository.TypeRepository;

@RestController
@RequestMapping("/api/types")
@CrossOrigin(origins = "*")
public class TypeController {

    @Autowired
    private TypeRepository repository;

    // Get all types
    @GetMapping
    public List<Type> getAllTypes() {
        return repository.findAll();
    }

    // Get type by ID
    @GetMapping("/{id}")
    public ResponseEntity<Type> getTypeById(@PathVariable Long id) {
        Type type = repository.findById(id).orElse(null);

        if (type == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(type);
    }

    // Add new type
    @PostMapping
    public Type addType(@RequestBody Type type) {
        return repository.save(type);
    }

    // Update type
    @PutMapping("/{id}")
    public ResponseEntity<Type> updateType(
            @PathVariable Long id,
            @RequestBody Type updatedType) {

        Type type = repository.findById(id).orElse(null);

        if (type == null) {
            return ResponseEntity.notFound().build();
        }

        type.setTypeName(updatedType.getTypeName());
        repository.save(type);

        return ResponseEntity.ok(type);
    }

    // Delete type
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteType(@PathVariable Long id) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.ok("Type deleted successfully");
    }
}
