package com.nicolascruz.platzi.persistence;

import com.nicolascruz.platzi.domain.Product;
import com.nicolascruz.platzi.domain.repository.ProductRepository;
import com.nicolascruz.platzi.persistence.crud.ProductoCrudRepository;
import com.nicolascruz.platzi.persistence.entity.Producto;
import com.nicolascruz.platzi.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //estereotipo de Spring Framework: se hace las operaciones que aplican a las tablas
public class ProductoRepository implements ProductRepository {
//public class ProductoRepository {

  @Autowired//los objetows que reciban esta anotacion LE VAN A CEDER EL CONTROL A SPRING PARA CONTROLAR ESAS INSTANCIAS
  private ProductoCrudRepository productoCrudRepository;
  @Autowired
  private ProductMapper mapper;//sale un error porque el @Mapper no lo reconoce como componente de spring tipo @Component

//  public List<Producto> getAll(){
  public List<Product> getAll(){
//    return (List<Producto>) productoCrudRepository.findAll();
    List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
    return mapper.toProducts(productos);
  }

  //>>>>>>> INICIO DE METODOS IMPLEMENTADOS DE ProductRepository

  @Override
  public Optional<List<Product>> getByCategory(int categoryId) {
    List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
    return Optional.of(mapper.toProducts(productos));
  }

  @Override
  public Optional<List<Product>> getScarseProducts(int quantity) {
     Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
    return productos.map(prods -> mapper.toProducts(prods));//se hace esto porque no tengo ningun mapeador que sea optional
  }

  @Override
  public Optional<Product> getProduct(int productId) {
    return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
  }

  @Override
  public Product save(Product product) {
    Producto producto = mapper.toProducto(product);
    return mapper.toProduct(productoCrudRepository.save(producto));
  }

  //>>>>>>> FIN DE METODOS IMPLEMENTADOS DE ProductRepository

  public List<Producto> getByCategoria(int idCategoria){//ya no serviria porque ahora viene con los metodos importados!!!!
    return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
  }

  public Optional<List<Producto>> getEscasos(int cantidad){//ya no serviria porque ahora viene con los metodos importados!!!!
    return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
  }

  public Optional<Producto> getProducto(int idProducto){//ya no serviria porque ahora viene con los metodos importados!!!!
    return productoCrudRepository.findById(idProducto);
  }

  public Producto save(Producto producto){//ya no serviria porque ahora viene con los metodos importados!!!!
    return productoCrudRepository.save(producto);
  }

  public void delete(int idProducto){
    productoCrudRepository.deleteById(idProducto);
  }

}
