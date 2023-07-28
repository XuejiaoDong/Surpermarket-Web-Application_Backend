package com.mercury.SpringBootRestDemo.service;

import com.mercury.SpringBootRestDemo.bean.OrderDetail;
import com.mercury.SpringBootRestDemo.bean.Product;
import com.mercury.SpringBootRestDemo.bean.ProductCart;
import com.mercury.SpringBootRestDemo.dao.OrderDetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailDao orderDetailDao;

    public List<OrderDetail> getOrderDetailsByOrderId(Integer orderId) {
        return orderDetailDao.findByOrderId(orderId);
    }

    public OrderDetail createOrderDetail(OrderDetail newOrderDetail) {
        // Some validation and logic here...

        return orderDetailDao.save(newOrderDetail);
    }

    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailDao.findAll();
    }

    public OrderDetail getOrderDetailById(Integer orderDetailId) {
        return orderDetailDao.findById(orderDetailId).orElse(null);
    }

    public List<Product> getProductsByOrderId(Integer orderId) {
        List<OrderDetail> orderDetails = orderDetailDao.findByOrderId(orderId);
        return orderDetails.stream()
                .map(OrderDetail::getProductCart)
                .map(ProductCart::getProduct)
                .collect(Collectors.toList());
    }

    public OrderDetail updateRefundRequest(Integer orderDetailId, Boolean refundRequest, String refundReason, Boolean agree) {
        OrderDetail orderDetail = orderDetailDao.findById(orderDetailId).orElse(null);
        if (orderDetail != null) {
            orderDetail.setRefundRequest(refundRequest);
            orderDetail.setRefundReason(refundReason);
            orderDetail.setAgree(agree);
            return orderDetailDao.save(orderDetail);
        }
        return null;
    }

    public OrderDetail updateReviewedStatus(Integer orderDetailId, Boolean reviewedStatus) {
        Optional<OrderDetail> optionalOrderDetail = orderDetailDao.findById(orderDetailId);
        if (optionalOrderDetail.isPresent()) {
            OrderDetail orderDetail = optionalOrderDetail.get();
            orderDetail.setReviewed(reviewedStatus);
            return orderDetailDao.save(orderDetail);
        }
        return null;
    }



}
