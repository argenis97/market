package dev.arsystem.domain.repository;

import java.util.List;
import java.util.Optional;

import dev.arsystem.domain.Product;

public interface ProductRepository {
	
	public List<Product> getAll();
	
	public List<Product> getByCategory(int categoryId);
	
	public List<Product> getScarseProduct(int quantity);
	
	public Optional<Product> getProduct(int productId);
	
	public Product save(Product product);
	
	public void delete(int productId);
}
