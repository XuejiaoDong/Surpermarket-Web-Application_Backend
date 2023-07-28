package com.mercury.SpringBootRestDemo.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_DETAIL")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer orderDetailId;

    @Column
    private Integer orderId;

    @Column
    private Integer productCartId;

    @Column
    private Integer quantity;

    @Column
    private Float unitPrice;

    @Column
    private Boolean refundRequest;

    @Column
    private String refundReason;

    @Column
    private Boolean agree;

    @Column
    private Boolean reviewed;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderId", referencedColumnName = "orderId", insertable = false, updatable = false)
    @JsonIgnore
    private Order order;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productCartId", referencedColumnName = "productCartId", insertable = false, updatable = false)
    private ProductCart productCart;


    @Transient
    public Integer getProductId() {
        return this.productCart != null ? this.productCart.getProductId() : null;
    }


    public OrderDetail() {
    }

    public OrderDetail(Integer orderDetailId, Integer orderId, Integer productCartId, Integer quantity, Float unitPrice, Boolean refundRequest, String refundReason, Boolean agree, Boolean reviewed, Order order, ProductCart productCart) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.productCartId = productCartId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.refundRequest = refundRequest;
        this.refundReason = refundReason;
        this.agree = agree;
        this.reviewed = reviewed;
        this.order = order;
        this.productCart = productCart;
    }

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductCartId() {
        return productCartId;
    }

    public void setProductCartId(Integer productCartId) {
        this.productCartId = productCartId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Boolean getRefundRequest() {
        return refundRequest;
    }

    public void setRefundRequest(Boolean refundRequest) {
        this.refundRequest = refundRequest;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public Boolean getAgree() {
        return agree;
    }

    public void setAgree(Boolean agree) {
        this.agree = agree;
    }

    public Boolean getReviewed() {
        return reviewed;
    }

    public void setReviewed(Boolean reviewed) {
        this.reviewed = reviewed;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ProductCart getProductCart() {
        return productCart;
    }

    public void setProductCart(ProductCart productCart) {
        this.productCart = productCart;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderDetailId=" + orderDetailId +
                ", orderId=" + orderId +
                ", productCartId=" + productCartId +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", refundRequest=" + refundRequest +
                ", refundReason='" + refundReason + '\'' +
                ", agree=" + agree +
                ", reviewed=" + reviewed +
                ", order=" + order +
                ", productCart=" + productCart +
                '}';
    }
}
