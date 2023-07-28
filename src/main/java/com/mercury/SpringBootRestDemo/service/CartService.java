package com.mercury.SpringBootRestDemo.service;

import com.mercury.SpringBootRestDemo.bean.Cart;
import com.mercury.SpringBootRestDemo.dao.CartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CartService {

    @Autowired
    private CartDao cartDao;



    public Cart getCartByUserId(int userId) {
        return cartDao.findByUserId(userId);
    }

    public void saveOrUpdateCart(Cart cart) {
        cartDao.save(cart);
    }

    public Cart createCart(Cart inputCart) {
        return cartDao.save(inputCart);
    }

    public Cart getCartById(int cartId) {
        return cartDao.findById(cartId).orElse(null);
    }

}


