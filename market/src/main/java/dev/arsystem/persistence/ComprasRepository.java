package dev.arsystem.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import dev.arsystem.domain.Purchase;
import dev.arsystem.domain.repository.PurchaseReporitory;
import dev.arsystem.persistence.crud.ComprasCrudRepository;
import dev.arsystem.persistence.entity.Compra;
import dev.arsystem.persistence.mapper.PurchaseMapper;

@Repository
public class ComprasRepository implements PurchaseReporitory {
	
	public ComprasRepository(ComprasCrudRepository repository, PurchaseMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	@Override
	public List<Purchase> getAll() {
		return mapper.toPurchaseList((List<Compra>) repository.findAll());
	}
	
	@Override
	public List<Purchase> getByClient(String clientId) {
		return mapper.toPurchaseList((List<Compra>) repository.findByIdCliente(clientId));
	}
	
	@Override
	public Purchase save(Purchase purchase) {
		Compra compra = mapper.toCompra(purchase);
		return mapper.toPurchase(repository.save(compra));
	}
	
	@Override
	public Optional<Purchase> findById(int id) {
		return repository.findById(id)
				.map(compra -> mapper.toPurchase(compra));
	}
	
	private ComprasCrudRepository repository;
	private PurchaseMapper mapper;
}
