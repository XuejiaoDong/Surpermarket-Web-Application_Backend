package com.mercury.SpringBootRestDemo.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @Column
    private Integer userId;

    @Column
    private Date date;

    @Column
    private Float subTotal;

    @Column
    private Float discount;

    @Column
    private Float shippingFee;

    @Column
    private Float tax;

    @Column
    private Float total;

    @Column
    private Boolean isRefund;

    @Column
    private String deliveryStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<OrderDetail> orderDetails = new HashSet<>();

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "userId", referencedColumnName = "userId", insertable = false, updatable = false)
    private User user;


    public Order() {
    }

    public Order(Integer orderId, Integer userId, Date date, Float subTotal, Float discount, Float shippingFee, Float tax, Float total, Boolean isRefund, String deliveryStatus, Set<OrderDetail> orderDetails, User user) {
        this.orderId = orderId;
        this.userId = userId;
        this.date = date;
        this.subTotal = subTotal;
        this.discount = discount;
        this.shippingFee = shippingFee;
        this.tax = tax;
        this.total = total;
        this.isRefund = isRefund;
        this.deliveryStatus = deliveryStatus;
        this.orderDetails = orderDetails;
        this.user = user;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Float subTotal) {
        this.subTotal = subTotal;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Float getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Float shippingFee) {
        this.shippingFee = shippingFee;
    }

    public Float getTax() {
        return tax;
    }

    public void setTax(Float tax) {
        this.tax = tax;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Boolean getRefund() {
        return isRefund;
    }

    public void setRefund(Boolean refund) {
        isRefund = refund;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", date=" + date +
                ", subTotal=" + subTotal +
                ", discount=" + discount +
                ", shippingFee=" + shippingFee +
                ", tax=" + tax +
                ", total=" + total +
                ", isRefund=" + isRefund +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                ", orderDetails=" + orderDetails +
                ", user=" + user +
                '}';
    }
}
