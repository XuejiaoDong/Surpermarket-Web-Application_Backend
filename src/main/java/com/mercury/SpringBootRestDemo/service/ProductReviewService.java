package com.mercury.SpringBootRestDemo.service;

import com.mercury.SpringBootRestDemo.bean.ProductReview;
import com.mercury.SpringBootRestDemo.dao.ProductReviewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductReviewService {

    private final ProductReviewDao productReviewDao;

    @Autowired
    public ProductReviewService(ProductReviewDao productReviewDao) {
        this.productReviewDao = productReviewDao;
    }

    public ProductReview addReview(ProductReview review) {
        // 设置评论的日期为当前日期
        review.setDate(new Date());

        // 保存评论到数据库
        return productReviewDao.save(review);
    }

    public List<ProductReview> getReviewsByProductId(int productId) {
        return productReviewDao.findByProductId(productId);
    }

}
