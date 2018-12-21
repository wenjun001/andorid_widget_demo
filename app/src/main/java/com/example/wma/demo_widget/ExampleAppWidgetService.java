package com.example.wma.demo_widget;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.text.DateFormatSymbols;

public class ExampleAppWidgetService extends Service {

    private static final String TAG="DEMO_APP_Servcie";



    private final String ACTION_UPDATE_ALL = "com.example.wma.widget.UPDATE_ALL                                                                                 ";

    private static final int UPDATE_TIME = 5000;

    private UpdateThread mUpdateThread;
    private Context mContext;

    private int count=0;






    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate---------");

        mUpdateThread = new UpdateThread();
        mUpdateThread.start();

        mContext = this.getApplicationContext();

        super.onCreate();
    }

    @Override
    public void onDestroy(){

        if (mUpdateThread != null) {
            mUpdateThread.interrupt();
        }

        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        super.onStartCommand(intent, flags, startId);

        return START_STICKY;
    }

    private class UpdateThread extends Thread {

        @Override
        public void run() {
            super.run();

            try {
                count = 0;
                while (true) {

                    count++;

                    Intent broadcastIntent = new Intent();
                    broadcastIntent.setAction(NewAppWidget.ACTION_UPDATE_ALL);

                    sendBroadcast(broadcastIntent);
                    Log.d(TAG, "run ... count:"+count);

                    Thread.sleep(UPDATE_TIME);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
