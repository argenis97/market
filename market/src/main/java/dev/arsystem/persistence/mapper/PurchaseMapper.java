package dev.arsystem.persistence.mapper;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import dev.arsystem.domain.Purchase;
import dev.arsystem.persistence.entity.Compra;

@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class})
public interface PurchaseMapper {
	
	@Mappings({
		@Mapping(target = "purchaseId", source = "idCompra")
		, @Mapping(target = "comment", source = "comentario")
		, @Mapping(target = "date", source = "fecha")
		, @Mapping(target = "paymentMethod", source = "medioPago")
		, @Mapping(target = "items", source = "comprasProducto")
		, @Mapping(target = "clientId", source = "idCliente")
		, @Mapping(target = "state", source = "estado")
	})
	public Purchase toPurchase(Compra compra);
	
	public List<Purchase> toPurchaseList(List<Compra> compras);
	
	@Mapping(target = "cliente", ignore = true)
	@InheritInverseConfiguration
	public Compra toCompra(Purchase purchase);
	
	@AfterMapping
	public default void afterMappingCompras(Object source, @MappingTarget Compra compra) {
		compra.getComprasProducto().forEach(producto -> producto.setCompra(compra));
	}
}
