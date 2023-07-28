package com.mercury.SpringBootRestDemo.dao;


import com.mercury.SpringBootRestDemo.bean.ProductCollection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCollectionDao extends JpaRepository<ProductCollection, Integer> {
    // 这里可以添加自定义的查询方法，例如根据userId查询所有的收藏
    List<ProductCollection> findByUserId(int userId);
    List<ProductCollection> findProductCollectionsByUserIdAndProductId(int userId, int productId);
    ProductCollection findProductCollectionByUserIdAndProductId(int userId, int productId);
}
