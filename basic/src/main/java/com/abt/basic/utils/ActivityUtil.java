package com.abt.basic.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import com.abt.basic.logger.LogHelper;

/**
 * @描述： @Activity工具类
 * @作者： @黄卫旗
 * @创建时间： @2017-11-25
 */
public final class ActivityUtil {

    private static final String TAG = ActivityUtil.class.getSimpleName();

    /**
     * 获取当前正在运行的activity名称
     * @param context
     * @return
     */
    public static final String getTopActivityName(Context context){
        ActivityManager manager = (ActivityManager)
                context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.RunningTaskInfo info = manager.getRunningTasks(1).get(0);
        return info.topActivity.getClassName(); //完整类名
    }

    /**
     * 通过packagename启动应用
     * @param context
     * @param packageName
     * */
    public static void startAPPFromPackageName(Context context, String packageName) {
        Intent intent = isExit(context,packageName);
        if (intent==null) {
            LogHelper.i(TAG,packageName+" not found!");
            ToastUtil.showToast("package not found!");
            return;
        }
        context.startActivity(intent);
    }

    /**
     * 通过packagename判断应用是否安装
     * @param context
     * @param packageName
     * @return 跳转的应用主activity Intent
     * */
    public static Intent isExit(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        Intent it = packageManager.getLaunchIntentForPackage(packageName);
        return it;
    }
}
