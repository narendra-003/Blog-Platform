package com.nova.blog.service;

import com.nova.blog.payload.CategoryDTO;

import java.util.List;

public interface CategoryService {

    // create
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    // update
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryID);

    // delete
    void deleteCategory(Integer categoryId);

    // get
    CategoryDTO getCategory(Integer categoryID);

    // get ALL
    List<CategoryDTO> getAllCategories();
}
