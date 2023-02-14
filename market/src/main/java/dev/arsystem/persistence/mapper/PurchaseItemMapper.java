package dev.arsystem.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import dev.arsystem.domain.PurchaseItem;
import dev.arsystem.persistence.entity.ComprasProducto;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, PurchaseMapper.class})
public interface PurchaseItemMapper {
	
	@Mappings({
		@Mapping(target = "active", source = "estado")
		, @Mapping(target = "product", source = "producto")
		, @Mapping(target = "productId", source = "id.idProducto")
		, @Mapping(target = "purchaseId", source = "id.idCompra")
		, @Mapping(target = "quantity", source = "cantidad")
	})
	public PurchaseItem toPurchaseProduct(ComprasProducto comprasProducto);
	
	@Mapping(target = "compra", ignore = true)
	@InheritInverseConfiguration
	public ComprasProducto toComprasProducto(PurchaseItem purchaseItem);
}
