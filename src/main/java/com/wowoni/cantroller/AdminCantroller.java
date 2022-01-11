package com.wowoni.cantroller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.wowoni.dto.ProductDTO;
import com.wowoni.model.Category;
import com.wowoni.model.Product;
import com.wowoni.service.CategoryServices;
import com.wowoni.service.ProductService;

@Controller
public class AdminCantroller {

	@Autowired
	CategoryServices categoryServices;
	@Autowired
	ProductService productService;

	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}

	@GetMapping("/admin/categories")
	public String getCategories(Model model) {
		model.addAttribute("categories", categoryServices.getAll());
		return "categories";
	}

	@GetMapping("/admin/categories/add")
	public String addCategories(Model model) {
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}

	@PostMapping("/admin/categories/add")
	public String postaddCategories(@ModelAttribute("category") Category category) {
		categoryServices.addCategory(category);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategory(@PathVariable int id) {
		categoryServices.deleteById(id);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/categories/update/{id}")
	public String updateCategory(@PathVariable int id, Model model) {
		Optional<Category> categoryOptional = categoryServices.updateCategorybyId(id);
		if (categoryOptional.isPresent()) {
			model.addAttribute("category", categoryOptional.get());
			return "categoriesAdd";
		} else
			return "404";
	}

	@GetMapping("/admin/products")
	public String getAllProducts(Model model) {
		model.addAttribute("products", productService.getAllProduct());
		return "products";
	}

	@GetMapping("/admin/products/add")
	public String addProductDto(Model model) {
		model.addAttribute("productDTO", new ProductDTO());
		model.addAttribute("categories", categoryServices.getAll());
		return "productsAdd";

	}

	@PostMapping("/admin/products/add")
	public String postAddProduct(@ModelAttribute("productDTO") ProductDTO productDTO,
			@RequestParam("productImage") MultipartFile file, @RequestParam("imgName") String imgName)
			throws Exception {
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setCategory(categoryServices.updateCategorybyId(productDTO.getCategoryId()).get());
		product.setPrice(productDTO.getPrice());
		product.setSellername(productDTO.getSellername());
		product.setDescreption(productDTO.getDescreption());
		String imgUUID;
		if (!file.isEmpty()) {
			imgUUID = file.getOriginalFilename();
			Path fileNameandPath = Paths.get(uploadDir, imgUUID);
			Files.write(fileNameandPath, file.getBytes());
		} else
			imgUUID = imgName;
		product.setImageName(imgUUID);
		productService.createProduct(product);

		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable long id) {
		productService.deleteProductbyId(id);
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/product/update/{id}")
	public String upadateProduct(@PathVariable long id, Model model) {
		Product product=productService.updateProduct(id).get();
		ProductDTO productDTO =new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setCategoryId(product.getCategory().getId());
		productDTO.setPrice(product.getPrice());
		productDTO.setSellername(product.getSellername());
		productDTO.setDescreption(product.getDescreption());
		productDTO.setImageName(product.getImageName());
		model.addAttribute("categories" , categoryServices.getAll());
		model.addAttribute("productDTO" , productDTO);
		return "productsAdd";
		
		
		
	}

}
