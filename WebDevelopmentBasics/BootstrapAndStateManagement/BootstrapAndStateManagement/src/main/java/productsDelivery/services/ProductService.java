package productsDelivery.services;

import productsDelivery.models.Product;
import productsDelivery.models.Status;
import productsDelivery.repository.ProductRepository;

import java.util.List;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService() {
        this.productRepository = new ProductRepository();
    }

    public List<Product> getProducts() {
        return this.productRepository.getProducts();
    }

    public Product getProductById(Long id) {
        return this.productRepository.getProduct(id);
    }

    public void save(Long id, Status status) {
        this.productRepository.persist(id,status);
    }
}
