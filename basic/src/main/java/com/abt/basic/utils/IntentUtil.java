package com.abt.basic.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述：     @Intent操作工具类
 * @作者：     @黄卫旗
 * @创建时间： @2017-04-25
 */
public final class IntentUtil {

    /**
     * 获取隐式Intent
     * @param context
     * @param action
     * @return
     */
    public static final Intent getExplicitIntent(Context context,String action){
        final PackageManager pm       = context.getPackageManager();
        final Intent implicitIntent   = new Intent(action);
        List<ResolveInfo> resolveInfos = pm.queryIntentServices(implicitIntent, 0);
        if (resolveInfos == null || resolveInfos.size() != 1) {
            return null;
        }
        ResolveInfo serviceInfo        = resolveInfos.get(0);
        String packageName             = serviceInfo.serviceInfo.packageName;
        String className               = serviceInfo.serviceInfo.name;
        ComponentName component        = new ComponentName(packageName, className);
        final Intent intent           = new Intent();
        intent.setComponent(component);
        return intent;
    }

    /**
     * 获取隐式Intent集合
     * @param context
     * @param action
     * @return
     */
    public static final List<Intent> getExplicitIntents(Context context,String action){
        final PackageManager pm       = context.getPackageManager();
        final Intent implicitIntent   = new Intent(action);
        List<ResolveInfo> resolveInfos = pm.queryIntentServices(implicitIntent, 0);
        if (resolveInfos == null) {
            return null;
        }

        final List<Intent> intents   = new ArrayList<>();
        for(ResolveInfo info:resolveInfos){
            String packageName             = info.serviceInfo.packageName;
            String className               = info.serviceInfo.name;
            ComponentName component        = new ComponentName(packageName, className);
            final Intent intent           = new Intent();
            intent.setComponent(component);
            intents.add(intent);
        }
        return intents;
    }
}
