package com.mercury.SpringBootRestDemo.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Table(name = "CART")
public class Cart {
    @Id
    private int cartId;

    @Column
    private int userId;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "cart")
    @JsonIgnore
    Set<ProductCart> productCarts = new HashSet<>();

    public Cart() {
    }

    public Cart(int cartId, int userId, Set<ProductCart> productCarts) {
        this.cartId = cartId;
        this.userId = userId;
        this.productCarts = productCarts;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Set<ProductCart> getProductCarts() {
        return productCarts;
    }

    public void setProductCarts(Set<ProductCart> productCarts) {
        this.productCarts = productCarts;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", userId=" + userId +
                ", productCarts=" + productCarts +
                '}';
    }
}
