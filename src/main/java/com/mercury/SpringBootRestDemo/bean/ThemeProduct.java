package com.mercury.SpringBootRestDemo.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "THEME_PRODUCT")
public class ThemeProduct {

    @Id
    private int themeProductId;
    @Column
    private int themeId;
    @Column
    private int productId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "themeId", referencedColumnName = "themeId", insertable = false, updatable = false)
    @JsonIgnore
    private Theme theme;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", referencedColumnName = "productId", insertable = false, updatable = false)
    private Product product;


    public ThemeProduct() {
    }

    public ThemeProduct(int themeProductId, int themeId, int productId, Theme theme, Product product) {
        this.themeProductId = themeProductId;
        this.themeId = themeId;
        this.productId = productId;
        this.theme = theme;
        this.product = product;
    }

    public int getThemeProductId() {
        return themeProductId;
    }

    public void setThemeProductId(int themeProductId) {
        this.themeProductId = themeProductId;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ThemeProduct{" +
                "themeProductId=" + themeProductId +
                ", themeId=" + themeId +
                ", productId=" + productId +
                ", theme=" + theme +
                ", product=" + product +
                '}';
    }
}
