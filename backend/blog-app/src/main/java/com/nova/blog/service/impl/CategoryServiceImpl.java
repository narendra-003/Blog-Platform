package com.nova.blog.service.impl;

import com.nova.blog.exception.ResourceNotFoundException;
import com.nova.blog.model.Category;
import com.nova.blog.payload.CategoryDTO;
import com.nova.blog.repository.CategoryRepository;
import com.nova.blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {

        Category category = this.modelMapper.map(categoryDTO, Category.class);
        Category savedCategory = this.categoryRepository.save(category);

        return this.modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryID) {
        Category category = categoryRepository.findById(categoryID).orElseThrow(() -> new ResourceNotFoundException("Category", "Category ID", categoryID));

        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        category.setCategoryDescription(category.getCategoryDescription());

        Category updatedCategory = this.categoryRepository.save(category);
        return this.modelMapper.map(updatedCategory, CategoryDTO.class);
    }

    @Override
    public void deleteCategory(Integer categoryID) {
        Category category = categoryRepository.findById(categoryID).orElseThrow(() -> new ResourceNotFoundException("Category", "Category ID", categoryID));

        this.categoryRepository.delete(category);
    }

    @Override
    public CategoryDTO getCategory(Integer categoryID) {
        Category category = categoryRepository.findById(categoryID).orElseThrow(() -> new ResourceNotFoundException("Category", "Category ID", categoryID));

        return this.modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {

        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = categories.stream().map((cat) -> this.modelMapper.map(cat, CategoryDTO.class)).toList();

        return categoryDTOS;
    }
}
