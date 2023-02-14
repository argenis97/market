package dev.arsystem.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.arsystem.domain.Purchase;
import dev.arsystem.domain.repository.PurchaseReporitory;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	
	public PurchaseServiceImpl(PurchaseReporitory repository) {
		this.repository = repository;
	}
	
	@Override
	public List<Purchase> getAll() {
		return repository.getAll();
	}
	
	@Override
	public List<Purchase> getByClient(String clientId) {
		return repository.getByClient(clientId);
	}
	
	@Override
	public Purchase save(Purchase purchase) {
		return repository.save(purchase);
	}
	
	@Override
	public Optional<Purchase> findById(int id) {
		return repository.findById(id);
	}
	
	private PurchaseReporitory repository;
}
