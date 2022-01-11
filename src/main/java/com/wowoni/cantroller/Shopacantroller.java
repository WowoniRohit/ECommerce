package com.wowoni.cantroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wowoni.service.CategoryServices;
import com.wowoni.service.ProductService;

@Controller
public class Shopacantroller {

	@Autowired
	CategoryServices categoryServices;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/")
	public String home(Model model) {
		return "index";
	}
	
	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("categories", categoryServices.getAll());
		model.addAttribute("products", productService.getAllProduct());
		return "shop";
	}
	
	
	@GetMapping("/shop/category/{id}")
	public String shopBycategorey(Model model,@PathVariable int id) {
		model.addAttribute("categories", categoryServices.getAll());
		model.addAttribute("products", productService.getProductByCategoryId(id));
		return "shop";
	}
	
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(@PathVariable int id, Model model) {
		model.addAttribute("product", productService.updateProduct(id).get());
		return "viewProduct";
		
	}
	
}
