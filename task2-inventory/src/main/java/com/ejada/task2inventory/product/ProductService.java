package com.ejada.task2inventory.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejada.task2inventory.exception.EntityCreationException;
import com.ejada.task2inventory.exception.EntityUpdateException;
import com.ejada.task2inventory.exception.NotFoundException;

@Service
public class ProductService {

	private ProductRepository productRepository;
	
	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
		
	public List<Product> getAllProducts() {
		try {return productRepository.findAll();} 
		catch (Exception e) {
			throw new NotFoundException("Could not find any products.");
		}
	}
	
	public Product getProductById(Integer product_id) {
		try {return productRepository.findById(product_id).get();} 
		catch (Exception e) {
			throw new NotFoundException("Could not find the product with the ID: "+product_id);
		}
	}
	
	public List<Product> getProductsForListOfIds(List<Integer> listOfProductIds){
		try {return productRepository.findAllById(listOfProductIds);} 
		catch (Exception e) {
			throw new NotFoundException("Could not find the wanted products.");
		}
	}
	
	public Product createProduct(ProductDto productDto) {
		Boolean inStock = false;
		if(productDto.getQuantity()>0)
			inStock = true;
		try {
			return productRepository.save(new Product(
				productDto.getName(), 
				productDto.getDescription(), 
				productDto.getPrice(),
				productDto.getQuantity(),
				inStock
			));
		} catch (Exception e) {
			throw new EntityCreationException("Error creating a new error.");
		}
	}
	
	public Product updateProduct(Product product) {
		try {return productRepository.save(product);} 
		catch (Exception e) {
			throw new EntityUpdateException("Cannot update this product.");
		}
	}

	public String deleteProduct(Integer id) {
		productRepository.softDelete(id);
		return "The product with the ID: ["+id+"] is deleted successfully";
	}
}