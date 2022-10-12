package com.mehery.mchat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class OnComplete extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "File Downloaded Successfully", Toast.LENGTH_SHORT).show();
    }
}
