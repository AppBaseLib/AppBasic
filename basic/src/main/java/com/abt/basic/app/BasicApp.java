package com.abt.basic.app;

import android.app.Application;

import com.abt.basic.BuildConfig;

/**
 * @描述： @基类application
 * @作者： @黄卫旗
 * @创建时间： @21/05/2018
 */
public abstract class BasicApp extends Application {

    private static BasicApp sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        init();
        initComplete();
    }

    public static final BasicApp getAppContext() {
        return sContext;
    }

    private final void init() {
        if (BuildConfig.DEBUG) {
            //DebugManage.initialize(this);
        }
    }

    public abstract void initComplete();

}
