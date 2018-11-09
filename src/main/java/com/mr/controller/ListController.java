package com.mr.controller;

import com.mr.model.OBJECTSku;
import com.mr.model.OBJECTTMallAttr;
import com.mr.model.TMallAttrVO;
import com.mr.model.TMallSkuAttrValueVO;
import com.mr.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Wy on 2018/11/6.
 */
@Controller
public class ListController {

    @Autowired
    private ListService listService;

    @RequestMapping("toListPage")
    public String toListPage(Integer flbh2,ModelMap map){


        List<TMallAttrVO> attrList = listService.selectAttrListByClass2(flbh2);

        List<OBJECTSku> skuList = listService.selectSkuListByClass2(flbh2);

        map.put("flbh2",flbh2);
        map.put("attrList",attrList);
        map.put("skuList",skuList);
        return "list";
    }


    @RequestMapping("selectSkuListByAttr")
    public String selectSkuListByAttr(TMallSkuAttrValueVO attrValueVO,
                                        Integer flbh2,ModelMap map){

        List<OBJECTSku> skuListByAttr = listService.selectSkuListByAttr(attrValueVO,flbh2);
        map.put("flbh2",flbh2);
        map.put("skuList",skuListByAttr);
        return "list-Sbox";
    }
}
