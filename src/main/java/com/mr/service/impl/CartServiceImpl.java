package com.mr.service.impl;

import com.mr.mapper.CartMapper;
import com.mr.model.TMallShoppingCar;
import com.mr.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wy on 2018/11/8.
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    public List<TMallShoppingCar> selectCartListByUserId(Integer userId) {
        return cartMapper.selectCartListByUserId(userId);
    }

    public void updateCartListByUserIdAndSkuId(Map<String, Object> cartMap) {
        cartMapper.updateCartListByUserIdAndSkuId(cartMap);
    }

    public void saveCart(TMallShoppingCar cart) {
        cartMapper.saveCart(cart);
    }

    public TMallShoppingCar selectCartByUserIdAndSkuId(Integer skuId, Integer userId) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("skuId",skuId);
        map.put("userId",userId);
        return cartMapper.selectCartByUserIdAndSkuId(map);
    }

    public void updateCartShfxzByUserIdAndSkuId(Integer skuId, Integer userId, String shfxz) {

        Map<String, Object> map = new HashMap<String, Object>();
            map.put("skuId",skuId);
            map.put("userId",userId);
            map.put("shfxz",shfxz);
        cartMapper.updateCartShfxzByUserIdAndSkuId(map);
    }

    public void deleteCartBySkuIdAndUserId(Integer skuId, Integer userId) {
        cartMapper.deleteCartBySkuIdAndUserId(skuId,userId);
    }
}
