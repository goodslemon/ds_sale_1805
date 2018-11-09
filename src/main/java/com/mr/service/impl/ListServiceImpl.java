package com.mr.service.impl;

import com.mr.mapper.ListMapper;
import com.mr.model.OBJECTSku;
import com.mr.model.TMallAttrVO;
import com.mr.model.TMallSkuAttrValueVO;
import com.mr.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wy on 2018/11/6.
 */
@Service
public class ListServiceImpl implements ListService {

    @Autowired
    private ListMapper listMapper;

    public List<TMallAttrVO> selectAttrListByClass2(Integer flbh2) {
        return listMapper.selectAttrListByClass2(flbh2);
    }

    public List<OBJECTSku> selectSkuListByClass2(Integer flbh2) {
        return listMapper.selectSkuListByClass2(flbh2);
    }

    public List<OBJECTSku> selectSkuListByAttr(TMallSkuAttrValueVO attrValueVO,
                                               Integer flbh2) {

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("flbh2",flbh2);
        map.put("attrValueList",attrValueVO.getAttrValueList());

        return listMapper.selectSkuListByAttr(map);
    }
}
