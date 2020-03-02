package com.example.securitytest.dao;

import com.example.securitytest.model.WorkOrder;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface WorkOrderMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(WorkOrder record);

    int insertSelective(WorkOrder record);

    WorkOrder selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(WorkOrder record);

    int updateByPrimaryKey(WorkOrder record);
}
