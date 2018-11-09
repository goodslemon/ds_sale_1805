package com.mr.mapper;

import com.mr.model.TMallShoppingCar;

import java.util.List;
import java.util.Map;

/**
 * Created by Wy on 2018/11/8.
 */
public interface CartMapper {
    List<TMallShoppingCar> selectCartListByUserId(Integer userId);

    void updateCartListByUserIdAndSkuId(Map<String, Object> cartMap);

    void saveCart(TMallShoppingCar cart);

    TMallShoppingCar selectCartByUserIdAndSkuId(Map<String, Object> map);

    void updateCartShfxzByUserIdAndSkuId(Map<String, Object> map);

    void deleteCartBySkuIdAndUserId(Integer skuId, Integer userId);
}
