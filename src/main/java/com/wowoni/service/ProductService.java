package com.wowoni.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wowoni.model.Product;
import com.wowoni.repository.ProductRepository;


@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	public void createProduct(Product product) {
		productRepository.save(product);
	}
	
	public List<Product> getAllProduct(){
		return productRepository.findAll();
	}
	
	public void deleteProductbyId(long id) {
		productRepository.deleteById(id);
	}
	
	public Optional<Product> updateProduct(long id){
		return productRepository.findById(id);
	}
	
	public List<Product> getProductByCategoryId(int id){
		return productRepository.findAllByCategory_Id(id);
	}
	
}
