package com.example.broadcastbestpractice.util;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.LogPrinter;

import com.example.broadcastbestpractice.activity.LoginActivity;
import com.example.broadcastbestpractice.activity.MainActivity;

/**
 * Created by 安维 on 2017/4/28.
 */

public class BaseActivity extends AppCompatActivity {
    ForceOffLineReceiver receiver=new ForceOffLineReceiver();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intent =new IntentFilter();
        intent.addAction("com.example.broacasttest.FORCE_OFFLINE");
        registerReceiver(receiver,intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
            unregisterReceiver(receiver);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
    class ForceOffLineReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("you are forced to offline.please try to login again.");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.removeAll();
                    Intent intent =new Intent(context, LoginActivity.class);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.removeAll();
                }
            });
            builder.show();
        }
    }
}
