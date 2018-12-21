package com.example.wma.demo_widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {
    private static final String TAG = "DEMO_APP_NewAppWidget";
    private final Intent EXAMPLE_SERVICE_INTENT =
            new Intent("android.appwidget.action.EXAMPLE_APP_WIDGET_SERVICE");

    static final String ACTION_UPDATE_ALL = "com.example.wma.widget.UPDATE_ALL";


    Intent newIntent;

    @Override
    public void onEnabled(Context context) {
        Log.d(TAG, "start onEnabled");
        try {
             newIntent = new Intent(context, ExampleAppWidgetService.class);

            context.startService(newIntent);

        }catch (Exception e){
            e.printStackTrace();
        }
        Log.d(TAG, "onEnabled DONE");


        super.onEnabled(context);
    }



    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "--------------OnReceive:Action: ");
        final String action = intent.getAction();
        Log.d(TAG, "OnReceive:Action: " + action);
        if (ACTION_UPDATE_ALL.equals(action)) {


        }

        super.onReceive(context, intent);
    }



    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }



    @Override
    public void onDisabled(Context context) {
        Log.d(TAG, "onDisabled");

        context.stopService(newIntent);

        super.onDisabled(context);
    }




}



