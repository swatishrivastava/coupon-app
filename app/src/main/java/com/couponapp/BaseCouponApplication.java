package com.couponapp;

import android.app.Application;
import android.content.Context;

public class BaseCouponApplication extends Application {
    private static Context appContext;

    public static Context getAppContext() {
        final Context result = appContext;
        if (result == null) {
            throw new IllegalStateException(
                    "Trying to access app context before the application has been created.");
        }
        return result;
    }

    public static void setAppContext(Context appContext) {
        BaseCouponApplication.appContext = appContext;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        setAppContext(getApplicationContext());
    }
}
