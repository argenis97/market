package dev.arsystem.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import dev.arsystem.domain.Category;
import dev.arsystem.persistence.entity.Categoria;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	
	@Mappings({
		@Mapping(source = "idCategoria", target = "categoryId")
		, @Mapping(source = "descripcion", target = "category")
		, @Mapping(source = "estado", target = "active")
	})
	public Category toCategory(Categoria categoria);
	
	@InheritInverseConfiguration
	@Mapping(target = "productos", ignore = true)
	public Categoria toCategoria(Category category);
}
