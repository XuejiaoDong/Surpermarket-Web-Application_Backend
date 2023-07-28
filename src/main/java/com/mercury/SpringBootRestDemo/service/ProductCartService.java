package com.mercury.SpringBootRestDemo.service;

import com.mercury.SpringBootRestDemo.bean.Cart;
import com.mercury.SpringBootRestDemo.bean.Product;
import com.mercury.SpringBootRestDemo.bean.ProductCart;
import com.mercury.SpringBootRestDemo.dao.ProductCartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductCartService {
    private ProductCartDao productCartDao;
    private CartService cartService;

    @Autowired
    public ProductCartService(ProductCartDao productCartDao, CartService cartService) {
        this.productCartDao = productCartDao;
        this.cartService = cartService;
    }



    public List<ProductCart> getAllProductCarts() {
        return productCartDao.findAll();
    }

    public ProductCart getProductCartById(int id) {
        return productCartDao.findById(id).orElse(null);
    }

    public List<ProductCart> getProductCartsByCartId(int cartId) {
        return productCartDao.findByCartId(cartId);
    }

    public void saveProductCart(ProductCart productCart) {
        productCartDao.save(productCart);
    }

//    public void deleteProductCart(ProductCart productCart) {
//        productCartDao.delete(productCart);
//    }

    @Transactional
    public boolean deleteProductCartById(int productCartId) {
        System.out.println("Trying to delete ProductCart with ID: " + productCartId);
        Optional<ProductCart> productCartOpt = productCartDao.findById(productCartId);
        if (productCartOpt.isPresent()) {
            productCartDao.deleteById(productCartId);
            System.out.println("Deleted ProductCart with ID: " + productCartId);
            return true;
        }
        System.out.println("Failed to delete ProductCart with ID: " + productCartId);
        return false;
    }




    public Integer getCartProductCount(int userId) {
        // 因为userId和cartId相同，所以直接使用userId作为cartId
        return getTotalProductCountByCartId(userId);
    }


    private Integer getTotalProductCountByCartId(int cartId) {
        return productCartDao.getTotalProductCountByCartId(cartId);
    }

//    public ProductCart addProductCartToUser(int userId, ProductCart productCart) {
//        productCart.setCartId(userId);
//        productCartDao.save(productCart);
//        return productCart;
//    }

    public ProductCart addProductCartToUser(int userId, ProductCart productCart) {
        int productId = productCart.getProductId();
        List<ProductCart> productCarts = getProductCartsByCartId(userId);
        for (ProductCart cart : productCarts) {
            if (cart.getProductId() == productId && !cart.isActive()) {
                // 如果购物车中已经存在该商品且为非活动状态(active为false)，则更新该商品数量
                cart.setQuantity(cart.getQuantity() + productCart.getQuantity());
                return productCartDao.save(cart);
            }
        }

        // 如果购物车中不存在该商品或者该商品为活动状态(active为true)，则添加到购物车
        productCart.setCartId(userId);
        if (productCart.getQuantity() == 0) {
            productCart.setQuantity(1);
        }
        return productCartDao.save(productCart);
    }




    public ProductCart addProductCartToUser(int userId, Product product) {
        // 查找购物车对象
        Cart cart = cartService.getCartByUserId(userId);

        if (cart == null) {
            throw new RuntimeException("No cart found for user with id " + userId);
        }

        // 创建新的 ProductCart 对象
        ProductCart productCart = new ProductCart();
        productCart.setProductId(product.getProductId());
        productCart.setCartId(cart.getCartId());
        productCart.setQuantity(1);
//        productCart.setActive(true);
//        productCart.setRefund(true);
        productCart.setCart(cart);
        productCart.setProduct(product);

        // 保存 ProductCart 对象到数据库
        productCartDao.save(productCart);

        return productCart;
    }

    public ProductCart updateProductCartQuantity(int productCartId, ProductCart updatedProductCart) {
        Optional<ProductCart> productCartOpt = productCartDao.findById(productCartId);
        if (!productCartOpt.isPresent()) {
            return null;
        }
        ProductCart productCart = productCartOpt.get();
        productCart.setQuantity(updatedProductCart.getQuantity());
        return productCartDao.save(productCart);
    }

    public List<ProductCart> getActiveProductCartsByUserId(int cartId) {
        return productCartDao.getActiveProductCartsByUserId(cartId);
    }



//    pubilc int updateIsActive  待order表和productCart之间关系完成后，在实施
}
