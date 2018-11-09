package com.mr.controller;

import com.mr.model.TMallShoppingCar;
import com.mr.model.TMallUserAccount;
import com.mr.service.CartService;
import com.mr.util.MyJsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wy on 2018/11/7.
 */
@Controller
public class CartController {


    @Autowired
    private CartService cartService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 使用 BigDecimal 计算合计
     *
     * @param cart
     * @return
     */
    public static Double getHj(TMallShoppingCar cart) {

        BigDecimal jg = new BigDecimal(cart.getSkuJg());
        BigDecimal thShl = new BigDecimal(cart.getTjshl());
        double hj = thShl.multiply(jg).doubleValue();
        return hj;
    }

    @RequestMapping("saveCart")
    public String saveCart(TMallShoppingCar cart, ModelMap map, HttpSession session,
                           @CookieValue(value = "cartCookieList", required = false)
                                   String cartCookieList, HttpServletResponse response,
                           HttpServletRequest request) throws Exception {
        //刚接收到就计算合计
        cart.setHj(getHj(cart));
        //通过session 获取key 的对象
        TMallUserAccount user = (TMallUserAccount) session.getAttribute("user");
        //定义购物车集合
        List<TMallShoppingCar> cartList = new ArrayList<TMallShoppingCar>();

        //判断session中用户 是否为空，为空是没有登录
        if (user == null) {
            //判断cookie中的值是否为空
            if (StringUtils.isBlank(cartCookieList)) {//数据为空，添加数据到购物车集合
                cartList.add(cart);//然后添加到cookie中
            } else {//有数据的话，判断商品是否存在
                cartList = MyJsonUtil.jsonToList(cartCookieList, TMallShoppingCar.class);
                boolean b = false;
                //遍历集合，判断集合中的SkuId 是否和 前台传递的SkuId 是否相同
                for (int i = 0; i < cartList.size(); i++) {
                    if (cartList.get(i).getSkuId() == cart.getSkuId()) {
                        //相等的话给 b 赋值
                        b = true;
                    }
                }
                if (b) {//传递的对象跟集合中的对象相等，把原始数量，加上传递的数量
                    for (int i = 0; i < cartList.size(); i++) {
                        cartList.get(i).setTjshl(cartList.get(i).getTjshl() +
                                cart.getTjshl());
                        cartList.get(i).setHj(getHj(cartList.get(i)));
                    }
                } else {
                    cartList.add(cart);//然后添加到cookie中
                }
            }
            String value = URLEncoder.encode(MyJsonUtil.objectToJson(cartList), "utf-8");
            //转换成Json字符串后存放到cookie中
            Cookie cookie = new Cookie("cartCookieList", value);
            //设置过期时间
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
        } else {//登录状态
            cartList = cartService.selectCartListByUserId(user.getId());
            if (cartList != null && cartList.size() > 0) {//db中有没有数据
                boolean flag = false;
                //循环遍历商品是否存在
                for (int i = 0; i < cartList.size(); i++) {
                    if (cartList.get(i).getSkuId() == cart.getSkuId()) {
                        flag = true;
                    }
                }
                if (flag) {//sku存在，更新数据
                    for (int i = 0; i < cartList.size(); i++) {
                        if (cartList.get(i).getSkuId() == cart.getSkuId()) {

                            Map<String, Object> cartMap = new HashMap<String, Object>();
                            cartMap.put("skuId", cartList.get(i).getSkuId());
                            cartMap.put("userId", user.getId());
                            cartMap.put("tjshl", cartList.get(i).getTjshl() + cart.getTjshl());
                            //循环一次后重新给数量赋值
                            cartList.get(i).setTjshl(cartList.get(i).getTjshl() + cart.getTjshl());
                            cartMap.put("hj", getHj(cartList.get(i)));
                            cartService.updateCartListByUserIdAndSkuId(cartMap);
                        }
                    }
                } else {//sku不存在，直接添加到购物车
                    cart.setYhId(user.getId());
                    cartService.saveCart(cart);
                }
            } else {
                cart.setYhId(user.getId());
                cartService.saveCart(cart);
            }
            redisTemplate.delete("redisCartUser_" + user.getId());
        }
        map.put("cart", cart);
        return "Cart-success";
    }


    @RequestMapping("selectMiniCart")
    public String selectMiniCart(HttpSession session, ModelMap map,
                                 @CookieValue(value = "cartCookieList", required = false)
                                         String cartCookieList) {

        List<TMallShoppingCar> cartList = new ArrayList<TMallShoppingCar>();
        TMallUserAccount user = (TMallUserAccount) session.getAttribute("user");
        if (user == null) {//没有登录的情况下，在cookie中查询
            cartList = MyJsonUtil.jsonToList(cartCookieList, TMallShoppingCar.class);
        } else {//登录情况下，先去redis中查询，没有再去db
            cartList = (List<TMallShoppingCar>) redisTemplate.opsForValue().get("redisCartUser_" + user.getId());
            if (cartList == null || cartList.size() == 0) {
                cartList = cartService.selectCartListByUserId(user.getId());
                //查询之后放到redis中
                redisTemplate.opsForValue().set("redisCartUser_" + user.getId(), cartList);
            }

        }
        Integer countSum = 0;
        for (int i = 0; i < cartList.size(); i++) {
            countSum += cartList.get(i).getTjshl();
        }
        map.put("cartList", cartList);
        map.put("countSum", countSum);
        map.put("sumHj", getSum(cartList));
        return "CartMiniInner";
    }




    //求购物车里的合计
    public BigDecimal getSum(List<TMallShoppingCar> cartList){
        BigDecimal sum = new BigDecimal("0");
        if (cartList==null||cartList.size()==0){
            return sum;
        }else {
            for (int i = 0; i < cartList.size(); i++) {
                if(cartList.get(i).getShfxz().equals("1")){//如果选择
                    sum = sum.add(new BigDecimal(cartList.get(i).getHj() + ""));
                }
            }
        }
        return sum;
    }


    @RequestMapping("toCartListPage")
    public String toCartListPage(HttpSession session, @CookieValue(value = "cartCookieList", required = false)
                                String cartCookieList, ModelMap map){

        List<TMallShoppingCar> cartList = new ArrayList<TMallShoppingCar>();
        //查询是否登录
        TMallUserAccount user = (TMallUserAccount) session.getAttribute("user");
        if(user ==null){//未登录，从cookie中获取集合
            cartList = MyJsonUtil.jsonToList(cartCookieList, TMallShoppingCar.class);
        }else {//登录
            //先去redis中查询
            cartList = (List<TMallShoppingCar>) redisTemplate.opsForValue().get("redisCartUser_" + user.getId());
            if (cartList==null||cartList.size()==0){//Redis中不存在，取数据库查询
                cartList = cartService.selectCartListByUserId(user.getId());
                //并将数据放到Redis
                redisTemplate.opsForValue().set("redisCartUser_"+user.getId(),cartList);
            }
        }
        map.put("cartList",cartList);
        map.put("hjSum",getSum(cartList));
        return "cartList";
    }


    @RequestMapping("changeShfxz")
    public String changeShfxz(int skuId,String shfxz,HttpSession session,
                     @CookieValue(value = "cartCookieList",required = false)
                             String cartCookieList,HttpServletResponse response,
                              ModelMap map) throws Exception {
        //获取session 中的值
        TMallUserAccount user = (TMallUserAccount) session.getAttribute("user");

        List<TMallShoppingCar> cartList = new ArrayList<TMallShoppingCar>();
        //判断是否登录
        if (user!=null){//登录
            //从redis中获取数据
            cartList = (List<TMallShoppingCar>) redisTemplate.opsForValue().get("redisCartUser_" + user.getId());
            for (int i = 0; i < cartList.size(); i++) {
                //如果skuId一样的话，修改该对象的状态。
                if (cartList.get(i).getSkuId() ==skuId){
                    //去数据库修改，是否被选中
                    cartService.updateCartShfxzByUserIdAndSkuId(skuId,user.getId(),shfxz);
                    //重新给定是否选中
                    cartList.get(i).setShfxz(shfxz);
                }
            }
            //同步到redis中
            redisTemplate.opsForValue().set("redisCartUser_"+user.getId(),cartList);
        }else {//没有登录
            //获得cookie中的数据
            cartList = MyJsonUtil.jsonToList(cartCookieList,TMallShoppingCar.class);
            for (int i = 0; i < cartList.size(); i++) {
                //如果skuId一样的话，修改该对象的状态。
                if(cartList.get(i).getSkuId() == skuId){
                    cartList.get(i).setShfxz(shfxz);
                }
            }
            //同步cookie
            String value = URLEncoder.encode(MyJsonUtil.objectToJson(cartList), "utf-8");
            //转换成Json字符串后存放到cookie中
            Cookie cookie = new Cookie("cartCookieList", value);
            //设置过期时间
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
        }
        map.put("cartList",cartList);
        //获得合计
        map.put("hjSum",getSum(cartList));
        return "cartListInner";
    }


    @RequestMapping("deleteCart")
    public String deleteCart(HttpSession session,int skuId,
                       @CookieValue(value = "cartCookieList",required = false)
                         String cartCookieList,HttpServletResponse response,
                             ModelMap map){

        TMallUserAccount user = (TMallUserAccount) session.getAttribute("user");

        List<TMallShoppingCar> cartList = new ArrayList<TMallShoppingCar>();

        if(user!=null){
            cartList= (List<TMallShoppingCar>) redisTemplate.opsForValue().get("redisCartUser_" + user.getId());
            for (int i = 0; i < cartList.size(); i++) {
                if(cartList.get(i).getSkuId()==skuId){
                    cartService.deleteCartBySkuIdAndUserId(skuId,user.getId());
                }
            }
            redisTemplate.opsForValue().set("redisCartUser_"+user.getId(),cartList);
        }else {
            cartList = MyJsonUtil.jsonToList(cartCookieList,TMallShoppingCar.class);
            Cookie cartCookie = new Cookie("cartCookieList", cartCookieList);
            for (int i = 0; i < cartList.size(); i++) {
                if(cartList.get(i).getSkuId()==skuId){
                    cartCookie.setMaxAge(0);
                    cartCookie.setPath("/");
                    response.addCookie(cartCookie);
                }else {
                    //String value = MyJsonUtil.objectToJson(cartList.get(i));
                }
            }
        }
        map.put("cartList",cartList);
        map.put("hjSum",getSum(cartList));
        return "CartMiniInner";
    }
}
