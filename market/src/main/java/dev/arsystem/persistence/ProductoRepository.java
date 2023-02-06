package dev.arsystem.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import dev.arsystem.domain.Product;
import dev.arsystem.domain.repository.ProductRepository;
import dev.arsystem.persistence.crud.ProductoCrudRepository;
import dev.arsystem.persistence.entity.Producto;
import dev.arsystem.persistence.mapper.ProductMapper;

@Repository
public class ProductoRepository implements ProductRepository {
	
	public ProductoRepository(ProductMapper mapper, ProductoCrudRepository repository) {
		this.mapper = mapper;
		productoRepository = repository;
	}
	
	public List<Product> getAll() {
		return mapper.toProducts((List<Producto>) productoRepository.findAll());
	}
	
	public List<Product> getByCategory(int categoryId){
		return mapper.toProducts(productoRepository.findByIdCategoriaOrderByNombreAsc(categoryId));
	}
	
	public List<Product> getScarseProduct(int quantity) {
		return mapper.toProducts(productoRepository.findByCantidadStockLessThanAndEstado(quantity, true));
	}
	
	public Optional<Product> getProduct(int productId) {
		return productoRepository.findById(productId)
				.map(product -> mapper.toProduct(product));
	}
	
	public Product save(Product product) {
		Producto producto = mapper.toProducto(product);
		return mapper.toProduct(productoRepository.save(producto));
	}
	
	public void delete(int productId) {
		productoRepository.deleteById(productId);
	}
	
	private ProductoCrudRepository productoRepository;
	private ProductMapper mapper;
}
