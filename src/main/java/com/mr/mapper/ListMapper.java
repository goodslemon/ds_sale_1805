package com.mr.mapper;

import com.mr.model.OBJECTSku;
import com.mr.model.TMallAttrVO;

import java.util.List;
import java.util.Map;

/**
 * Created by Wy on 2018/11/6.
 */
public interface ListMapper {


    List<TMallAttrVO> selectAttrListByClass2(Integer flbh2);

    List<OBJECTSku> selectSkuListByClass2(Integer flbh2);

    List<OBJECTSku> selectSkuListByAttr(Map<String, Object> map);
}
