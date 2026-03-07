package com.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.expensetracker.model.Type;

public interface TypeRepository extends JpaRepository<Type, Long> {

}
