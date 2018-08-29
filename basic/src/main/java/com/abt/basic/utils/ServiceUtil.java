package com.abt.basic.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @描述：     @service操作工具类
 * @作者：     @黄卫旗
 * @创建时间： @2017-04-25
 */
public final class ServiceUtil {

    /**
     * 判断服务是否运行
     * @param context
     * @param serviceName
     * @return
     */
    public static final boolean isServiceRunning(Context context, String serviceName){
        if (!TextUtils.isEmpty(serviceName) && context != null) {
            ActivityManager activityManager
                    = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            ArrayList<ActivityManager.RunningServiceInfo> runningServiceInfoList
                    = (ArrayList<ActivityManager.RunningServiceInfo>)
                    activityManager.getRunningServices(100);
            for (Iterator<ActivityManager.RunningServiceInfo> iterator = runningServiceInfoList.iterator(); iterator.hasNext();) {
                ActivityManager.RunningServiceInfo runningServiceInfo  = (ActivityManager.RunningServiceInfo) iterator.next();
                if (serviceName.equals(runningServiceInfo.service.getClassName().toString())) {
                    return true;
                }
            }
        } else {
            return false;
        }
        return false;
    }

}
