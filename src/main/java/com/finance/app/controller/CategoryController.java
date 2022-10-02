package com.finance.app.controller;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.finance.app.domain.model.Category;
import com.finance.app.service.CategoryService;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("api/v1/category")
    @ResponseStatus(HttpStatus.OK)
    public List<Category> findCategories() {
        return categoryService.findCategories();
    }

    @PutMapping("api/v1/category")
    @ResponseStatus(HttpStatus.OK)
    public UUID addOrUpdateCategory(@RequestBody Category category) {
        return categoryService.createOrUpdateCategory(category).getId();
    }

    @DeleteMapping("api/v1/category/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable("categoryId") final UUID categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleDataIntegrityViolationException(
            DataIntegrityViolationException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Entity already exists!");
    }
}
