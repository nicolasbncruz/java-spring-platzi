package com.nicolascruz.platzi.web.controller;

import com.nicolascruz.platzi.domain.Product;
import com.nicolascruz.platzi.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
  @Autowired
  private ProductService productService;

  @GetMapping("/all")
  public List<Product> getAll(){
    return productService.getAll();
  }

  @GetMapping("/product")
  public Optional<Product> getProduct(int productId){
    return productService.getProduct(productId);
  }

  @GetMapping("/")
  public Optional<List<Product>> getByCategory(int categoryId){
    return productService.getByCategory(categoryId);
  }

  @PostMapping("/")
  public Product save(Product product){
    return productService.save(product);
  }

  @PostMapping("/")
  public boolean delete(int productId){
    return productService.delete(productId);
  }

}
