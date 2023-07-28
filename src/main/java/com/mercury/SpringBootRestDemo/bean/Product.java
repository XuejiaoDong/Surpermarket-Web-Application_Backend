package com.mercury.SpringBootRestDemo.bean;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    @Column
    private String name;
    @Column
    private Double price;
    @Column
    private String image;
    @Column
    private int stock;
    @Column
    private String description;

    @Column
    private String category;

    @Column
    private boolean organic;

    @Column
    private String status;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "product")
    @JsonIgnore
    Set<ThemeProduct> themeProducts = new HashSet<>();

    public Product() {
    }

    public Product(int productId, String name, Double price, String image, int stock, String description, String category, boolean organic, String status, Set<ThemeProduct> themeProducts) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.image = image;
        this.stock = stock;
        this.description = description;
        this.category = category;
        this.organic = organic;
        this.status = status;
        this.themeProducts = themeProducts;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isOrganic() {
        return organic;
    }

    public void setOrganic(boolean organic) {
        this.organic = organic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<ThemeProduct> getThemeProducts() {
        return themeProducts;
    }

    public void setThemeProducts(Set<ThemeProduct> themeProducts) {
        this.themeProducts = themeProducts;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", stock=" + stock +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", organic=" + organic +
                ", status='" + status + '\'' +
                ", themeProducts=" + themeProducts +
                '}';
    }
}

