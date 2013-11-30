package com.logxen.smoothieclient;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;

public class UsbSerialService extends Service {
	private Messenger mMessenger;
	private final IBinder mBinder = new UsbSerialBinder();
	public class UsbSerialBinder extends Binder {
		UsbSerialService getService() {
			return UsbSerialService.this;
		}
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		return mBinder;
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		return true; // allow rebinding
	}
	
	@Override
	public void onRebind(Intent intent) {
		
	}

	private Looper mLooper;
	private UsbSerialServiceHandler mHandler;

	@Override
	public void onCreate() {
		HandlerThread thread = new HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND);
		thread.start();
		mLooper = thread.getLooper();
		mHandler = new UsbSerialServiceHandler(mLooper);
		mMessenger = new Messenger(mHandler);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Message msg = mHandler.obtainMessage();
		msg.arg1 = startId;
		mHandler.sendMessage(msg);

		Intent notificationIntent = new Intent(this, MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
		@SuppressWarnings("deprecation")
		Notification notification = new Notification.Builder(this)
			.setTicker(getText(R.string.notification_ticker))
			.setContentTitle(getText(R.string.notification_title))
			.setContentText(getText(R.string.notification_message))
			.setSmallIcon(R.drawable.ic_launcher)
			.setContentIntent(pendingIntent)
			.getNotification();
		startForeground(startId, notification);

		return START_NOT_STICKY;
	}

	@Override
	public void onDestroy() {	}
}
