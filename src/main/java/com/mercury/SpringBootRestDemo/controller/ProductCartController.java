package com.mercury.SpringBootRestDemo.controller;

import com.mercury.SpringBootRestDemo.bean.ProductCart;
import com.mercury.SpringBootRestDemo.dao.ProductCartDao;

import com.mercury.SpringBootRestDemo.service.ProductCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-carts")
public class ProductCartController {
    @Autowired
    private ProductCartService productCartService;

    @Autowired
    private ProductCartDao productCartDao;

    @GetMapping("/cart-count/{userId}")
    public ResponseEntity<Integer> getCartProductCount(@PathVariable("userId") int userId) {
        Integer count = productCartService.getCartProductCount(userId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProductCart>> getProductCartsByUserId(@PathVariable("userId") int userId) {
        List<ProductCart> productCarts = productCartService.getProductCartsByCartId(userId);
        return ResponseEntity.ok(productCarts);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<ProductCart> addProductCartToUser(@PathVariable("userId") int userId, @RequestBody ProductCart productCart) {
        ProductCart savedProductCart = productCartService.addProductCartToUser(userId, productCart);
        return ResponseEntity.ok(savedProductCart);
    }

    @PutMapping("/{productCartId}")
    public ResponseEntity<ProductCart> updateProductCartCount(@PathVariable("productCartId") int productCartId, @RequestBody ProductCart updatedProductCart) {
        ProductCart productCart = productCartDao.findById(productCartId).orElse(null);
        if (productCart != null) {
            productCart.setQuantity(updatedProductCart.getQuantity());
            productCart.setActive(updatedProductCart.isActive());
            // 其他需要更新的属性...
            productCart = productCartDao.save(productCart);
            return ResponseEntity.ok(productCart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{productCartId}")
    public ResponseEntity<?> deleteProductCart(@PathVariable("productCartId") int productCartId) {
        boolean success = productCartService.deleteProductCartById(productCartId);
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
