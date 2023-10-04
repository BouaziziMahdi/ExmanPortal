package com.bank.banking.controlleurs;

import com.bank.banking.Services.impl.CategoryServiceImpl;
import com.bank.banking.dto.Categorydto;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
@Tag(name = "categories", description = "the categories API")
public class CategoriesController {
    private final CategoryServiceImpl categoryService;

    @PostMapping("/")
    public ResponseEntity<Integer> save(
            @RequestBody Categorydto categorydto
    ) {
        return ResponseEntity.ok(categoryService.addCategory(categorydto));
    }
    @GetMapping("/{category-id}")
    public ResponseEntity<Categorydto> findById(
            @PathVariable("category-id") Integer id
    ) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }
    @GetMapping("/")
    public ResponseEntity<List<Categorydto>> findAll() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
    @PutMapping("/")
    public Integer updateCategory(
            @RequestBody Categorydto categorydto) {
        return this.categoryService.updateCategory(categorydto);
    }
    @DeleteMapping("/{category-id}")
    public void deleteCategory(
            @PathVariable("category-id") Integer contactId
    ) {
        this.categoryService.deleteCategory(contactId);
    }
}
