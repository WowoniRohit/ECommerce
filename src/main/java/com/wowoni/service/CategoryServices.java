package com.wowoni.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wowoni.model.Category;
import com.wowoni.repository.CategoryRepository;

@Service
public class CategoryServices {

	@Autowired
	CategoryRepository categoryRepository;
	
	public void addCategory(Category category) {
		categoryRepository.save(category);
	}
	
	public List<Category> getAll(){
		return categoryRepository.findAll();
	}
	
	public void deleteById(int id) {
		categoryRepository.deleteById(id);
	}
	
	public Optional<Category> updateCategorybyId(int id) {
		return categoryRepository.findById(id);
	}
}
