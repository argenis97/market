package dev.arsystem.persistence.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import dev.arsystem.persistence.entity.Compra;

public interface ComprasCrudRepository extends CrudRepository<Compra, Integer> {
	public List<Compra> findByIdCliente(String idCliente);
}
