package com.nova.blog.controller;

import com.nova.blog.payload.ApiResponse;
import com.nova.blog.payload.CategoryDTO;
import com.nova.blog.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;

    }

    // create
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {

        CategoryDTO categoryResponseDTO = this.categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok().body(categoryResponseDTO);
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable int id) {

        CategoryDTO categoryResponseDTO = this.categoryService.updateCategory(categoryDTO, id);
        return ResponseEntity.ok().body(categoryResponseDTO);
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int id) {

        this.categoryService.deleteCategory(id);
        return ResponseEntity.ok().body(new ApiResponse("Category deleted successfully.", true));
    }

    // get
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable int id) {

        CategoryDTO categoryResponseDTO = this.categoryService.getCategory(id);
        return ResponseEntity.ok().body(categoryResponseDTO);
    }

    // get all
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {

        List<CategoryDTO> categoryResponseDTOS = this.categoryService.getAllCategories();
        return ResponseEntity.ok().body(categoryResponseDTOS);
    }
}
