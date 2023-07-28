package com.mercury.SpringBootRestDemo.bean;

import javax.persistence.*;

@Entity
@Table(name = "product_collection")

public class ProductCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int productCollectionId;

    @Column
    private int productId;

    @Column
    private int userId;

    @Column
    private boolean isActive;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId", referencedColumnName = "productId", insertable = false, updatable = false)
    private Product product;

    public ProductCollection() {
    }

    public ProductCollection(int productCollectionId, int productId, int userId, boolean isActive, Product product) {
        this.productCollectionId = productCollectionId;
        this.productId = productId;
        this.userId = userId;
        this.isActive = isActive;
        this.product = product;
    }

    public int getProductCollectionId() {
        return productCollectionId;
    }

    public void setProductCollectionId(int productCollectionId) {
        this.productCollectionId = productCollectionId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductCollection{" +
                "productCollectionId=" + productCollectionId +
                ", productId=" + productId +
                ", userId=" + userId +
                ", isActive=" + isActive +
                ", product=" + product +
                '}';
    }
}
