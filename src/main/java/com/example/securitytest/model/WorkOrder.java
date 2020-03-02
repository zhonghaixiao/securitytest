package com.example.securitytest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkOrder {
    private Long orderId;
    private Long activityId;
    private Long goodsId;
    private Long price;
    private Integer count;
    private String userName;
    private Byte orderState;
}
