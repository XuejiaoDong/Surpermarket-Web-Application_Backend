package com.mercury.SpringBootRestDemo.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_CART")
public class ProductCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int productCartId;

    @Column
    private int productId;

    @Column
    private int cartId;
    @Column
    private int quantity;
    @Column
    private boolean isActive;

    @Column
    private boolean isRefund;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cartId", referencedColumnName = "cartId", insertable = false, updatable = false)
    @JsonIgnore
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId", referencedColumnName = "productId", insertable = false, updatable = false)
    private Product product;

//
//    @OneToOne(fetch = FetchType.LAZY)
//    private OrderDetail orderDetail;



    public ProductCart() {
    }

    public ProductCart(int productId, int cartId, int quantity, boolean isActive, boolean isRefund, Cart cart, Product product) {
        this.productId = productId;
        this.cartId = cartId;
        this.quantity = quantity;
        this.isActive = true;
        this.isRefund = false;
        this.cart = cart;
        this.product = product;
    }




    public int getProductCartId() {
        return productCartId;
    }

    public void setProductCartId(int productCartId) {
        this.productCartId = productCartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean isRefund() {
        return isRefund;
    }

    public void setRefund(boolean refund) {
        isRefund = refund;
    }

    @Override
    public String toString() {
        return "ProductCart{" +
                "productCartId=" + productCartId +
                ", productId=" + productId +
                ", cartId=" + cartId +
                ", count=" + quantity +
                ", isActive=" + isActive +
                ", isRefund=" + isRefund +
                ", cart=" + cart +
                ", product=" + product +
                '}';
    }
}

