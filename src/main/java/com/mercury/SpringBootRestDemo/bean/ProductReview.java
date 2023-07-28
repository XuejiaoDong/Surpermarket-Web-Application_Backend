package com.mercury.SpringBootRestDemo.bean;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product_review")
public class ProductReview {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int reviewId;

    @Column
    private int productId;

    @Column
    private String review;

    @Column
    private int userId;

    @Column
    private Date date;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId", referencedColumnName = "productId", insertable = false, updatable = false)
    private Product product;


    public ProductReview() {
    }

    public ProductReview(int reviewId, int productId, String review, int userId, Date date, Product product) {
        this.reviewId = reviewId;
        this.productId = productId;
        this.review = review;
        this.userId = userId;
        this.date = date;
        this.product = product;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductReview{" +
                "reviewId=" + reviewId +
                ", productId=" + productId +
                ", review='" + review + '\'' +
                ", userId=" + userId +
                ", date=" + date +
                ", product=" + product +
                '}';
    }
}
