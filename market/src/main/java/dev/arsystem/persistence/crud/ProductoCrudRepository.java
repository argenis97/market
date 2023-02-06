package dev.arsystem.persistence.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import dev.arsystem.persistence.entity.Producto;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
	public List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);
	
	public List<Producto> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}
