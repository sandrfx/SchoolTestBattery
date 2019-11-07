package com.sandrlab.testbattery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements IBatteryListener {

    private static final int IMAGE_LEVEL_STEP = 10000;

    private BatteryBroadcastReceiver mBroadcastReceiver;
    private TextView mTxtIsCharging;
    private ImageView mImgBattery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtIsCharging = findViewById(R.id.txt_battery_charging);
        mImgBattery = findViewById(R.id.img_battery);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setupBroadcastReceiver();
    }

    @Override
    protected void onStop() {
        super.onStop();
        destroyBroadCastReceiver();
    }

    private void setupBroadcastReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);

        mBroadcastReceiver = new BatteryBroadcastReceiver(this);

        registerReceiver(mBroadcastReceiver, filter);
    }

    private void destroyBroadCastReceiver() {
        unregisterReceiver(mBroadcastReceiver);
        mBroadcastReceiver = null;
    }


    @Override
    public void onBatteryLevelChanged(BatteryInfo batteryInfo) {
        mTxtIsCharging.setText(String.valueOf(batteryInfo.isCharging()));
        mImgBattery.setImageLevel((int) (batteryInfo.getBatteryLevel() * IMAGE_LEVEL_STEP));
    }
}
