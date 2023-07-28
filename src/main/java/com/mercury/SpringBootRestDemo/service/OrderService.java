package com.mercury.SpringBootRestDemo.service;

import com.mercury.SpringBootRestDemo.bean.DailySales;
import com.mercury.SpringBootRestDemo.bean.Order;
import com.mercury.SpringBootRestDemo.bean.OrderDetail;
import com.mercury.SpringBootRestDemo.bean.ProductCart;
import com.mercury.SpringBootRestDemo.dao.OrderDao;
import com.mercury.SpringBootRestDemo.dao.OrderDetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    // Assuming cartItems is a List of ProductCart objects representing the current items in the cart.
    public Order createOrderFromCart(Integer userId, List<ProductCart> cartItems, Float total, Float discount,Boolean Refund,String DeliveryStatus) {
        Order newOrder = new Order();
        newOrder.setUserId(userId);
        newOrder.setTotal(total);
        newOrder.setDiscount(discount);
        // Set other properties as needed...

        newOrder.setRefund(Refund);
        newOrder.setDeliveryStatus(DeliveryStatus);


        // Save the new Order.
        newOrder = orderDao.save(newOrder);

        for (ProductCart item : cartItems) {
            OrderDetail newOrderDetail = new OrderDetail();
            newOrderDetail.setOrderId(newOrder.getOrderId());
            newOrderDetail.setProductCartId(item.getProductCartId());
            newOrderDetail.setQuantity(item.getQuantity());
            // Set other properties as needed...

            // Save the new OrderDetail.
            orderDetailDao.save(newOrderDetail);
        }

        return newOrder;
    }

    @Autowired
    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }

    public Order getOrderById(Integer orderId) {
        return orderDao.findById(orderId).orElse(null);
    }

    public Order createOrder(Order order) {
        return orderDao.save(order);
    }

    @PersistenceContext
    private EntityManager entityManager;

    public List<DailySales> getDailySales() {
        // Implementation depends on your repository and database structure
        // This is just an example and might not work in your specific case
        List<Object[]> resultList = entityManager.createQuery(
                "SELECT CAST(o.date AS date), COUNT(o) " +
                        "FROM Order o " +
                        "GROUP BY CAST(o.date AS date)")
                .getResultList();

        return resultList.stream()
                .map(result -> new DailySales(((java.sql.Date) result[0]).toLocalDate(), ((Long) result[1]).intValue()))
                .collect(Collectors.toList());
    }

    public void updateDeliveryStatus(Integer orderId, String deleveryStatus) {
        Order order = getOrderById(orderId);
        if (order != null) {
            order.setDeliveryStatus(deleveryStatus);

            orderDao.save(order);
        } else {
            throw new IllegalArgumentException("no this order");
        }
    }
}
