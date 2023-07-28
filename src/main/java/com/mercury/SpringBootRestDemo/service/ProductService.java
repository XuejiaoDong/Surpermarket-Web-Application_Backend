package com.mercury.SpringBootRestDemo.service;
import com.mercury.SpringBootRestDemo.bean.Product;
import com.mercury.SpringBootRestDemo.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    public List<Product> getAllProducts() {
        return productDao.findAll();
    }
    public Product getProductById(int id) {
        return productDao.findById(id).orElse(null);
    }
    public Product createProduct(Product product) {
        return productDao.save(product);
    }

    public Product updateProduct(int productId, Product product) {
        Product existingProduct = getProductById(productId);
        if (existingProduct != null) {
            // Update the existing product with the new information
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setStock(product.getStock());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setImage(product.getImage());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setOrganic(product.isOrganic());
            existingProduct.setStatus(product.getStatus()); // 添加此行以更新产品的状态字段
            return productDao.save(existingProduct);
        }
        return null;
    }


    public void deleteProduct(int productId) {
        productDao.deleteById(productId);
    }
}
