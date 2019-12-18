package priv.jesse.mall.service;

import priv.jesse.mall.entity.OrderItem;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 购物车
 */
public interface ShopCartService {
    String NAME_PREFIX = "shop_cart_";

    void addCart(int productId, HttpServletRequest request) throws Exception;

    void remove(int productId, HttpServletRequest request) throws Exception;

    List<OrderItem> listCart(HttpServletRequest request) throws Exception;
}
