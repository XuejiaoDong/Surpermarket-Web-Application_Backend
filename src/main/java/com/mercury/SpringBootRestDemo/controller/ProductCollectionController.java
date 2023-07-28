package com.mercury.SpringBootRestDemo.controller;

import com.mercury.SpringBootRestDemo.bean.ProductCollection;
import com.mercury.SpringBootRestDemo.service.ProductCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-collections")
public class ProductCollectionController {

    @Autowired
    private ProductCollectionService productCollectionService;

    @GetMapping("/{userId}")
    public List<ProductCollection> getProductCollectionsByUserId(@PathVariable int userId) {
        return productCollectionService.getProductCollectionsByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<?> saveProductCollection(@RequestBody ProductCollection productCollection) {
        // 检查商品收藏是否已存在
        if (productCollectionService.doesProductCollectionExist(productCollection.getUserId(), productCollection.getProductId())) {
            // 如果已存在，返回一个错误消息
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This product is already in your collection.");
        } else {
            // 如果不存在，保存新的商品收藏
            ProductCollection savedProductCollection = productCollectionService.saveProductCollection(productCollection);
            return ResponseEntity.ok(savedProductCollection);
        }
    }

    @DeleteMapping("/{userId}/{productId}")
    public void deleteProductCollection(@PathVariable int userId, @PathVariable int productId) {
        productCollectionService.deleteProductCollection(userId, productId);
    }

    @GetMapping("/user/{userId}/product/{productId}")
    public boolean isProductFavoritedByUser(@PathVariable int userId, @PathVariable int productId) {
        return productCollectionService.isProductFavoritedByUser(userId, productId);
    }



}
