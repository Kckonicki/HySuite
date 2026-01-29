package com.Buddytohelpu.hysuite.data;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;

/**
 * Dashboard statistics and activity data
 */
public class DashboardData {
    public static final BuilderCodec<DashboardData> CODEC = BuilderCodec.<DashboardData>builder(DashboardData.class, DashboardData::new)
        .addField(new KeyedCodec<>("Action", Codec.STRING), (d, s) -> d.action = s, d -> d.action)
        .build();

    public String action;

    public DashboardData() {
        this.action = "";
    }
}
