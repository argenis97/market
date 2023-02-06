package dev.arsystem.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.arsystem.domain.Product;
import dev.arsystem.domain.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Override
	public List<Product> getAll() {
		return productRepository.getAll();
	}
	
	@Override
	public List<Product> getByCategory(int categoryId) {
		return productRepository.getByCategory(categoryId);
	}
	
	@Override
	public List<Product> getScarseProduct(int quantity) {
		return productRepository.getScarseProduct(quantity);
	}
	
	@Override
	public Optional<Product> getProduct(int productId) {
		return productRepository.getProduct(productId);
	}
	
	@Override
	@Transactional
	public Product save(Product product) {
		return productRepository.save(product);
	}
	
	@Override
	@Transactional
	public boolean delete(int productId) {
		return getProduct(productId)
			.map(product -> {
				productRepository.delete(productId);
				return true;
			}).orElse(false);
	}
	
	private ProductRepository productRepository;
}
