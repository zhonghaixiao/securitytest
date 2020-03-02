package com.example.securitytest.dao;

import com.example.securitytest.model.Goods;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsMapper {
    int deleteByPrimaryKey(Long goodsId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long goodsId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);
}
