package dev.arsystem.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import dev.arsystem.domain.Product;
import dev.arsystem.persistence.entity.Producto;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {
	
	@Mappings({
		@Mapping(source = "idProducto", target = "productId")
		, @Mapping(source = "nombre", target = "name")
		, @Mapping(source = "cantidadStock", target = "stock")
		, @Mapping(source = "idCategoria", target = "categoryId")
		, @Mapping(source = "precioVenta", target = "price")
		, @Mapping(source = "estado", target = "active")
		, @Mapping(source = "categoria", target = "category")
	})
	public Product toProduct(Producto producto);
	
	public List<Product> toProducts(List<Producto> productos);
	
	@InheritInverseConfiguration
	@Mapping(target = "codigoBarras", ignore = true)
	@Mapping(target = "comprasProducto", ignore = true)
	public Producto toProducto(Product product);
}
