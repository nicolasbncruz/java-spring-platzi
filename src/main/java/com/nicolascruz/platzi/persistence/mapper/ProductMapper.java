package com.nicolascruz.platzi.persistence.mapper;

import com.nicolascruz.platzi.domain.Product;
import com.nicolascruz.platzi.persistence.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {

  @Mappings({
      @Mapping(source = "idProducto", target = "productId"),
      @Mapping(source = "nombre", target = "name"),
      @Mapping(source = "idCategoria", target = "categoryId"),
      @Mapping(source = "precioVenta", target = "price"),
      @Mapping(source = "cantidadStock", target = "stock"),
      @Mapping(source = "estado", target = "active"),
      @Mapping(source = "categoria", target = "category")
  })
  Product toProduct(Producto producto);
  List<Product> toProducts(List<Producto> productos);

  @InheritInverseConfiguration
  @Mapping(target = "codigoBarras", ignore = true)
  Producto toProducto(Product product);

}
