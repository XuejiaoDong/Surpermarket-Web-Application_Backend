package com.mercury.SpringBootRestDemo.controller;


import com.mercury.SpringBootRestDemo.bean.DailySales;
import com.mercury.SpringBootRestDemo.bean.OrderDetail;
import com.mercury.SpringBootRestDemo.bean.Product;
import com.mercury.SpringBootRestDemo.bean.ProductCart;
import com.mercury.SpringBootRestDemo.service.OrderDetailService;
import com.mercury.SpringBootRestDemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/product-sales")
    public Map<String, Integer> getProductSales() {
        List<OrderDetail> orderDetails = orderDetailService.getAllOrderDetails();
        Map<String, Integer> sales = new HashMap<>();

        for (OrderDetail orderDetail : orderDetails) {
            // Assuming ProductCart has a getProduct method
            ProductCart productCart = orderDetail.getProductCart();
            Product product = productCart.getProduct();
            String productName = product.getName();

            int quantity = orderDetail.getQuantity();
            sales.put(productName, sales.getOrDefault(productName, 0) + quantity);
        }

        return sales;
    }
    @Autowired
    private OrderService orderService;

    @GetMapping("/daily-sales")
    public List<DailySales> getDailySales() {
        return orderService.getDailySales();
    }


}
