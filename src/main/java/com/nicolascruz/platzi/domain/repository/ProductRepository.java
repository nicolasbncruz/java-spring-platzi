package com.nicolascruz.platzi.domain.repository;

import com.nicolascruz.platzi.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
  List<Product> getAll();
  Optional<List<Product>> getByCategory(int categoryId);
  Optional<List<Product>> getScarseProducts(int quantity);//productos escasos
  Optional<Product> getProduct(int productId);
  Product save(Product product);
  void delete(int productId);
  //con esto defnimos las reglas que va a tener nuestro repository
  //siempre estemos hablando en terminos del dominio y no de las entidades o tablas.
}
