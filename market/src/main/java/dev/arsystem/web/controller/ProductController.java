package dev.arsystem.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.arsystem.domain.Product;
import dev.arsystem.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	public ProductController(ProductService service) {
		this.service = service;
	}
	
	@GetMapping("/all")
	@ApiOperation("Get All Supermarket Products")
	@ApiResponse(code = 200, message = "OK")
	public ResponseEntity<List<Product>> getAll() {
		return Optional.of(service.getAll())
				.filter(products -> !products.isEmpty())
				.map(ResponseEntity::ok)
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/{id}")
	@ApiOperation("Search a Product With a ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK")
		, @ApiResponse(code = 404, message = "Product not Found")
	})
	public ResponseEntity<Product> getProduct(@ApiParam(value = "The id of the Product", required = true, example = "7") @PathVariable("id") int productId) {
		return service.getProduct(productId)
				.map(product -> ResponseEntity.ok(product))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId) {
		return Optional.of(service.getByCategory(categoryId))
				.filter(products -> !products.isEmpty())
				.map(products -> ResponseEntity.ok(products))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") int productId) {
		return Optional.of(service.delete(productId))
				.filter(del -> del)
				.map(del -> new ResponseEntity<>(HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping
	public ResponseEntity<Product> save(@RequestBody Product product) {
		return new ResponseEntity<Product>(service.save(product), HttpStatus.CREATED);
	}
	
	private ProductService service;
}
