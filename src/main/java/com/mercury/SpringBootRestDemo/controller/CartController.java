package com.mercury.SpringBootRestDemo.controller;

import com.mercury.SpringBootRestDemo.bean.Cart;
import com.mercury.SpringBootRestDemo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Cart> createCart(@RequestBody Cart inputCart) {
        Cart cart = cartService.createCart(inputCart);
        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Cart> getCartById(@PathVariable int id) {
        Cart cart = cartService.getCartById(id);
        if (cart != null) {
            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
