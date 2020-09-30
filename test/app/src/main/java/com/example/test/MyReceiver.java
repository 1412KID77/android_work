package com.example.test;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @SuppressLint("ShowToast")
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if(Intent.ACTION_BATTERY_OKAY.equals(intent.getAction())){
            Toast.makeText(context, "电量恢复", Toast.LENGTH_LONG).show();
        }
        if(Intent.ACTION_BATTERY_LOW.equals(intent.getAction())){
            Toast.makeText(context, "电量过低", Toast.LENGTH_LONG).show();
        }
        if(Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())){
            Bundle bundle = intent.getExtras();
            int current = bundle.getInt("level");
            int total = bundle.getInt("scale");
            System.out.println("现在的电池电量是" + current);
            Toast.makeText(context,current + "现在的电池电量是", Toast.LENGTH_LONG).show();

        }

    }


}
