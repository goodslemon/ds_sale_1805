package com.mr.service;

import com.mr.model.TMallShoppingCar;

import java.util.List;
import java.util.Map;

/**
 * Created by Wy on 2018/11/8.
 */
public interface CartService {
    List<TMallShoppingCar> selectCartListByUserId(Integer id);

    void updateCartListByUserIdAndSkuId(Map<String, Object> cartMap);

    void saveCart(TMallShoppingCar cart);

    TMallShoppingCar selectCartByUserIdAndSkuId(Integer skuId, Integer id);

    void updateCartShfxzByUserIdAndSkuId(Integer skuId, Integer id, String shfxz);

    void deleteCartBySkuIdAndUserId(Integer skuId, Integer id);
}
