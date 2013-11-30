package com.logxen.smoothieclient;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class UsbSerialServiceHandler extends Handler {
	public UsbSerialServiceHandler(Looper looper){
		super(looper);
	}
	
	@Override
	public void handleMessage(Message msg) {
		switch(msg.what) { 
		//TODO: handle messages
		default:
			super.handleMessage(msg);
		}
	}

}
