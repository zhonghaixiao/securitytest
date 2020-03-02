package com.example.securitytest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ActivityGoodsKey {
    private Long activityId;
    private Long goodsId;
}
