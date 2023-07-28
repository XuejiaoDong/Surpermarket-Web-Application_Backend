package com.mercury.SpringBootRestDemo.service;

import com.mercury.SpringBootRestDemo.bean.ProductCollection;
import com.mercury.SpringBootRestDemo.dao.ProductCollectionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCollectionService {

    @Autowired
    private ProductCollectionDao productCollectionDao;

    public List<ProductCollection> getProductCollectionsByUserId(int userId) {
        return productCollectionDao.findByUserId(userId);
    }

    public ProductCollection saveProductCollection(ProductCollection productCollection) {
        return productCollectionDao.save(productCollection);
    }

    public boolean doesProductCollectionExist(int userId, int productId) {
        List<ProductCollection> productCollections = productCollectionDao.findProductCollectionsByUserIdAndProductId(userId, productId);
        return !productCollections.isEmpty();
    }

    public void deleteProductCollection(int userId, int productId) {
        ProductCollection productCollection = productCollectionDao.findProductCollectionByUserIdAndProductId(userId, productId);
        if(productCollection != null){
            productCollectionDao.delete(productCollection);
        }
    }

    public boolean isProductFavoritedByUser(int userId, int productId) {
        List<ProductCollection> collections = productCollectionDao.findByUserId(userId);
        for (ProductCollection collection : collections) {
            if (collection.getProductId() == productId) {
                return true;
            }
        }
        return false;
    }


}
