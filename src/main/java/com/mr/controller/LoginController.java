package com.mr.controller;

import com.mr.model.TMallShoppingCar;
import com.mr.model.TMallUserAccount;
import com.mr.service.CartService;
import com.mr.service.UserService;
import com.mr.util.MyCookieUtils;
import com.mr.util.MyJsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wy on 2018/11/5.
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private CartService cartService;

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping("toLoginPage")
    public String toLoginPage(){

        return "login";
    }

    /**
     * 登录方法
     * @param userName
     * @param passWord
     * @return
     */
    @RequestMapping("login")
    public String login(String userName, String passWord,
                        HttpSession session, HttpServletRequest request,
                        HttpServletResponse response ,
                        @CookieValue(value="cartCookieList",required = false )
                                    String cartCookieList ) {

        //根据传递的账户密码查询出来实体
        TMallUserAccount user = userService.selectUserByNamePs(userName,passWord);
        //判断查询的用户是否为null，为null返回登录页面
        if (user == null){
            return "redirect:toLoginPage.do";
        }else {
            session.setAttribute("user",user);
            String yhMch = user.getYhMch();
            //将用户名称放到cookie中
            Cookie cookie = new Cookie("yhMch", yhMch);
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
            //判断才cookie中是否有值
            if(!StringUtils.isBlank(cartCookieList)){
                List<TMallShoppingCar> cartListCookie = MyJsonUtil.jsonToList(cartCookieList, TMallShoppingCar.class);

                List<TMallShoppingCar> cartListDb = cartService.selectCartListByUserId(user.getId());

                //循环cookie对象
                for (int i = 0; i < cartListCookie.size(); i++) {
                    //根据用户Id,SkuId获取 商品，判断商品是否存在
                    TMallShoppingCar cart =
                            cartService.selectCartByUserIdAndSkuId(cartListCookie.get(i).getSkuId(),user.getId());
                    //商品存在，则更新DB商品
                    if(cart !=null){
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("skuId",cartListCookie.get(i).getSkuId());
                        map.put("userId",user.getId());
                        map.put("tjshl",cartListCookie.get(i).getTjshl()+cart.getTjshl());
                        //修改对象的数量
                        cartListCookie.get(i).setTjshl(cartListCookie.get(i).getTjshl()+cart.getTjshl());
                        map.put("hj",CartController.getHj(cartListCookie.get(i)));

                        cartService.updateCartListByUserIdAndSkuId(map);
                    }else {
                        cartListCookie.get(i).setYhId(user.getId());
                        cartService.saveCart(cartListCookie.get(i));
                    }
                }
                Cookie cartCookie = new Cookie("cartCookieList", cartCookieList);
                cartCookie.setMaxAge(0);
                cartCookie.setPath("/");
                response.addCookie(cartCookie);
                redisTemplate.delete("redisCartUser_"+user.getId());
            }
            //cookie中没有值直接返回
            return "redirect:toMainPage.do";
        }
    }





    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:toLoginPage.do";
    }
}
