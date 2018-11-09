package com.mr.controller;

import com.mr.model.TMallSku;
import com.mr.model.TMallSkuItemVO;
import com.mr.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Wy on 2018/11/7.
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("toItemPage")
    public String toItemPage(Integer skuId, Integer spuId,ModelMap map){

        TMallSkuItemVO itmVO = itemService.selectSkuBySkuId(skuId);

        List<TMallSku> skuList = itemService.selectskuListBySpuId(spuId);

        map.put("itmVO",itmVO);
        map.put("skuList",skuList);
        return "item";
    }
}

