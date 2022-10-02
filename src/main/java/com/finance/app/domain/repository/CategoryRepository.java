package com.finance.app.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finance.app.domain.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
