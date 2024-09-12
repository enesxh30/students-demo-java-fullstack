package com.sda.student.service;

import com.sda.student.dto.CategoryDto;
import com.sda.student.entity.Category;
import com.sda.student.mapper.CategoryMapper;
import com.sda.student.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    public CategoryDto save(CategoryDto categoryDto) {
        Category category = categoryMapper.mapToEntity(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.mapToDto(savedCategory);
    }

    public CategoryDto findCategoryById(long categoryId) {
        Category foundCategory = categoryRepository.findById(categoryId).orElseThrow(
                                () -> new RuntimeException("Category with id: " + categoryId + " was not found"));
        return categoryMapper.mapToDto(foundCategory);
    }

    public List<CategoryDto> findAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> returnCategoryDto = new ArrayList<>();
        for (Category category : categories) {
            returnCategoryDto.add(categoryMapper.mapToDto(category));
        }
        return returnCategoryDto;
    }

    public CategoryDto updateById(CategoryDto categoryDto, long categoryId) {
        Category foundCategory = categoryRepository.findById(categoryId).orElseThrow(
                                () -> new RuntimeException("Category with id: " + categoryId + " was not found"));
        foundCategory.setId(categoryId);
        foundCategory.setName(categoryDto.getName());
        foundCategory.setDescription(categoryDto.getDescription());
        Category updatedCategory = categoryRepository.save(foundCategory);
        return categoryMapper.mapToDto(updatedCategory);
    }


    public void deleteById(long categoryId) {
        Category foundCategory = categoryRepository.findById(categoryId).orElseThrow(
                ()-> new RuntimeException("Category with id: " + categoryId + " was not found"));
        categoryRepository.delete(foundCategory);
    }
}
