package dev.arsystem.domain.service;

import java.util.List;
import java.util.Optional;

import dev.arsystem.domain.Product;

public interface ProductService {
	
	public List<Product> getAll();
	
	public List<Product> getByCategory(int categoryId);
	
	public List<Product> getScarseProduct(int quantity);
	
	public Optional<Product> getProduct(int productId);
	
	public Product save(Product product);
	
	public boolean delete(int productId);
}
