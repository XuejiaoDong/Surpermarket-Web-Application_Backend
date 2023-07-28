package com.mercury.SpringBootRestDemo.dao;
import com.mercury.SpringBootRestDemo.bean.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductReviewDao extends JpaRepository<ProductReview, Integer> {
    List<ProductReview> findByProductId(int productId);

}
