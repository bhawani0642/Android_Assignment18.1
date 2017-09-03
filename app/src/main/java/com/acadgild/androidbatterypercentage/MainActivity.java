package com.acadgild.androidbatterypercentage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //CREATING OBJECT OF TEXTVIEW
    TextView batteryPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initilazing the texview
        batteryPercent = (TextView) findViewById(R.id.txtPercentage);
        //calling getPercentage() method
        getPercentage();

    }

    //getPercentage method
    private void getPercentage() {
        //creating object of BroadcastReciver
        BroadcastReceiver myReciever = new BroadcastReceiver() {
            //overriding the onReceive method
            @Override
            public void onReceive(Context context, Intent intent) {

                context.unregisterReceiver(this);
                int currentLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int level = -1;

                if (currentLevel >= 0 && scale > 0) {
                    level = (currentLevel * 100) / scale;
                }//setting the texview to current battery level
                batteryPercent.setText("Battery Level Remaining: " + level + "%");

            }
        };
        //passing the intentFilter and in it passing Action_battery_changed
        IntentFilter batteryLevelFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(myReciever, batteryLevelFilter);

    }
}


