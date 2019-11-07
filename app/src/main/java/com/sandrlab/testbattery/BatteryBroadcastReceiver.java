package com.sandrlab.testbattery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

public class BatteryBroadcastReceiver extends BroadcastReceiver {

    private WeakReference<IBatteryListener> mBatteryListener;

    public BatteryBroadcastReceiver(@NonNull IBatteryListener batteryListener) {
        mBatteryListener = new WeakReference<>(batteryListener);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        IBatteryListener listener = mBatteryListener.get();
        if (listener != null) {
            listener.onBatteryLevelChanged(createBatteryInfo(intent));
        }
    }

    private BatteryInfo createBatteryInfo(Intent intent) {
        float batteryLevel = getBatteryLevel(intent);
        boolean isCharging = isCharging(intent);

        return new BatteryInfo(batteryLevel, isCharging);
    }

    private float getBatteryLevel(Intent intent) {
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        return level / (float) scale;
    }

    private boolean isCharging(Intent intent) {
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        return status == BatteryManager.BATTERY_STATUS_CHARGING;
    }
}
