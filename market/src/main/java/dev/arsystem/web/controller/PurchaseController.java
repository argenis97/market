package dev.arsystem.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.arsystem.domain.Purchase;
import dev.arsystem.domain.service.PurchaseService;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
	
	public PurchaseController(PurchaseService service) {
		this.service = service;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Purchase>> getAll() {
		return Optional.of(service.getAll())
				.filter(purchases -> !purchases.isEmpty())
				.map(ResponseEntity::ok)
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/client/{id}")
	public ResponseEntity<List<Purchase>> getByClient(@PathVariable("id") String clientId) {
		return Optional.of(service.getByClient(clientId))
				.filter(purchases -> !purchases.isEmpty())
				.map(ResponseEntity::ok)
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Purchase> getById(int id) {
		return service.findById(id)
				.map(ResponseEntity::ok)
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping
	public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
		return new ResponseEntity<Purchase>(service.save(purchase), HttpStatus.CREATED);
	}
	
	private PurchaseService service;
}
