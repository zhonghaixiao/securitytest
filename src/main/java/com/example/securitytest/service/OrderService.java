package com.example.securitytest.service;

import com.example.securitytest.dao.ActivityGoodsMapper;
import com.example.securitytest.dao.WorkOrderMapper;
import com.example.securitytest.exception.NoInventoryException;
import com.example.securitytest.model.ActivityGoods;
import com.example.securitytest.model.ActivityGoodsKey;
import com.example.securitytest.model.WorkOrder;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import zhong.example.util.IdWorker;

@Service
public class OrderService {

    @Autowired
    private ActivityGoodsMapper activityGoodsMapper;
    @Autowired
    private WorkOrderMapper workOrderMapper;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public boolean placeOrder(long activityId, long goodsId, String userId){
        //1.查询商品信息
        ActivityGoods activityGoods = activityGoodsMapper.selectByPrimaryKey(
                ActivityGoodsKey.builder().activityId(activityId).goodsId(goodsId).build());
        int inventory = activityGoods.getInventory();
        int goodsNum = activityGoods.getNum();
        if (inventory < goodsNum){
            throw new NoInventoryException("库存不足");
        }
        //2.减库存
        activityGoods.setInventory(inventory-2);
        int c = activityGoodsMapper.updateByPrimaryKey(activityGoods);
        if (c != 1){
            throw new RuntimeException("更新库存错误");
        }
        //3.落订单
        c = workOrderMapper.insert(WorkOrder.builder()
                .activityId(activityGoods.getActivityId())
                .orderState((byte) 0)
                .goodsId(activityGoods.getGoodsId())
                .count(activityGoods.getNum())
                .price(activityGoods.getPrice())
                .userName(userId)
                .orderId(IdWorker.next())
                .build());
        if (c != 1){
            throw new RuntimeException("sql错误");
        }
        return c == 1;
    }


}
