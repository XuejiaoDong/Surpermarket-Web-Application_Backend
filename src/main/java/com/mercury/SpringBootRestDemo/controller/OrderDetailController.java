package com.mercury.SpringBootRestDemo.controller;

import com.mercury.SpringBootRestDemo.bean.OrderDetail;
import com.mercury.SpringBootRestDemo.bean.Product;
import com.mercury.SpringBootRestDemo.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderDetails")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailService.getAllOrderDetails();
    }

    @GetMapping("/{orderDetailId}")
    public OrderDetail getOrderDetailById(@PathVariable Integer orderDetailId) {
        return orderDetailService.getOrderDetailById(orderDetailId);
    }

    @GetMapping("/orders/{orderId}/products")
    public List<Product> getProductsByOrderId(@PathVariable Integer orderId) {
        return orderDetailService.getProductsByOrderId(orderId);
    }

    @PostMapping
    public ResponseEntity<OrderDetail> createOrderDetail(@RequestBody OrderDetail orderDetail) {
        try {
            OrderDetail createdOrderDetail = orderDetailService.createOrderDetail(orderDetail);
            return new ResponseEntity<>(createdOrderDetail, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            System.err.println("Error while creating order detail: " + e);
            // Return a 500 Internal Server Error status code to the client
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{orderDetailId}")
    public ResponseEntity<OrderDetail> updateRefundRequest(@PathVariable Integer orderDetailId, @RequestBody OrderDetail details) {
        OrderDetail updatedOrderDetail = orderDetailService.updateRefundRequest(orderDetailId, details.getRefundRequest(), details.getRefundReason(), details.getAgree());

        if (updatedOrderDetail == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedOrderDetail, HttpStatus.OK);
    }
    @PutMapping("/{orderDetailId}/reviewed")
    public ResponseEntity<OrderDetail> updateReviewedStatus(@PathVariable Integer orderDetailId, @RequestBody OrderDetail details) {
        OrderDetail updatedOrderDetail = orderDetailService.updateReviewedStatus(orderDetailId, details.getReviewed());

        if (updatedOrderDetail == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedOrderDetail, HttpStatus.OK);
    }


}
