package com.sandrlab.testbattery;

public class BatteryInfo {

    private final float mBatteryLevel;
    private final boolean mIsCharging;

    public BatteryInfo(float batteryLevel, boolean isCharging) {
        mBatteryLevel = batteryLevel;
        mIsCharging = isCharging;
    }

    public float getBatteryLevel() {
        return mBatteryLevel;
    }

    public boolean isCharging() {
        return mIsCharging;
    }
}
