package com.mr.mapper;

import com.mr.model.TMallSku;
import com.mr.model.TMallSkuItemVO;

import java.util.List;

/**
 * Created by Wy on 2018/11/7.
 */
public interface ItemMapper {
    TMallSkuItemVO selectSkuBySkuId(Integer skuId);

    List<TMallSku> selectskuListBySpuId(Integer spuId);
}
