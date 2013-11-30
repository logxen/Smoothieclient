package com.logxen.smoothieclient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DeviceAttachedReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent mainActivityIntent = new Intent(context, MainActivity.class);
		context.startActivity(mainActivityIntent);
	}
}
