package vn.thinhhuynh.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.thinhhuynh.laptopshop.domain.Product;
import vn.thinhhuynh.laptopshop.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product getProductById(long id) {
        return this.productRepository.findById(id);
    }

    public void deleteProduct(long id) {
        this.productRepository.deleteById(id);
    }

    public Product handleSaveProduct(Product product) {
        Product eric = this.productRepository.save(product);
        return eric;
    }
}
