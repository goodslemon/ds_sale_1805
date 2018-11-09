package com.mr.service;

import com.mr.model.OBJECTSku;
import com.mr.model.TMallAttrVO;
import com.mr.model.TMallSkuAttrValueVO;

import java.util.List;

/**
 * Created by Wy on 2018/11/6.
 */
public interface ListService {
    List<TMallAttrVO> selectAttrListByClass2(Integer flbh2);

    List<OBJECTSku> selectSkuListByClass2(Integer flbh2);

    List<OBJECTSku> selectSkuListByAttr(TMallSkuAttrValueVO attrValueVO,Integer flbh2);
}
