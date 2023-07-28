package com.mercury.SpringBootRestDemo.dao;

import com.mercury.SpringBootRestDemo.bean.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCartDao extends JpaRepository<ProductCart, Integer> {
    List<ProductCart> findByCartId(int cartId);
    @Query("SELECT COUNT(pc) FROM ProductCart pc WHERE pc.cartId = :userId")
    Integer getTotalProductCountByCartId(int userId);

    Optional<ProductCart> findByCartIdAndProductId(int cartId, int productId);
    @Query("SELECT pc FROM ProductCart pc WHERE pc.cartId = :cartId AND pc.isActive = false")
    List<ProductCart> getActiveProductCartsByUserId(int cartId);
}
