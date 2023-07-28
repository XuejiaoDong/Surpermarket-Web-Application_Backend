package com.mercury.SpringBootRestDemo.controller;

import com.mercury.SpringBootRestDemo.bean.Order;
import com.mercury.SpringBootRestDemo.bean.OrderDetail;
import com.mercury.SpringBootRestDemo.bean.Product;
import com.mercury.SpringBootRestDemo.service.OrderDetailService;
import com.mercury.SpringBootRestDemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Integer orderId) {
        return orderService.getOrderById(orderId);
    }


    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<String> updateDeliveryStatus(@PathVariable Integer orderId, @RequestParam("deliveryStatus") String deliveryStatus) {
        try {
            orderService.updateDeliveryStatus(orderId, deliveryStatus);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error: " + e.getMessage());
        }
    }
    @GetMapping("/{orderId}/details")
    public List<OrderDetail> getOrderDetailsByOrderId(@PathVariable Integer orderId) {
        List<OrderDetail> details = orderDetailService.getOrderDetailsByOrderId(orderId);
        for (OrderDetail detail : details) {
            System.out.println("Product ID: " + detail.getProductId());
        }
        return details;
    }



}
