package dev.arsystem.domain.service;

import java.util.List;
import java.util.Optional;

import dev.arsystem.domain.Purchase;

public interface PurchaseService {
	
	public List<Purchase> getAll();
	
	public List<Purchase> getByClient(String clientId);
	
	public Purchase save(Purchase purchase);
	
	public Optional<Purchase> findById(int id);
}
