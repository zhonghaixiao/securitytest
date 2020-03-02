package com.example.securitytest.dao;

import com.example.securitytest.model.ActivityGoods;
import com.example.securitytest.model.ActivityGoodsKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityGoodsMapper {
    int deleteByPrimaryKey(ActivityGoodsKey key);

    int insert(ActivityGoods record);

    int insertSelective(ActivityGoods record);

    ActivityGoods selectByPrimaryKey(ActivityGoodsKey key);

    int updateByPrimaryKeySelective(ActivityGoods record);

    int updateByPrimaryKey(ActivityGoods record);
}
