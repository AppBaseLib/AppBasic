package com.abt.basic.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.abt.basic.app.BasicApp;

/**
 * @描述：     @获取资源工具类
 * @作者：     @黄卫旗
 * @创建时间： @2017-05-10
 */
public class ResourceUtil {

    /**
     * 根据资源ID返回字符串
     *
     * @param resid
     * @return
     */
    public static final String getString(int resid) {
        return BasicApp.getAppContext().getString(resid);
    }

    /**
     * 获取dimens文件
     * @param resid
     * @return
     */
    public static final int getDimensionPixelOffset(int resid){
        return BasicApp.getAppContext().
                getResources().getDimensionPixelOffset(resid);
    }

    /**
     * 根据资源ID和传入的参数进行格式化返回字符串
     *
     * @param resid      资源id
     * @param formatArgs 格式化参数数组
     * @return
     */
    public static final String getString(int resid, Object... formatArgs) {
        return BasicApp.getAppContext().getString(resid, formatArgs);
    }

    /**
     * 获取颜色
     */
    public static final int getColor(int resid) {
        return BasicApp.getAppContext().getResources().getColor(resid);
    }

    /**
     * 获取图片
     */
    public static final Drawable getDrawable(int resid) {
        return BasicApp.getAppContext().getResources().getDrawable(resid);
    }

    /**
     * 获取dimems大小（单位为像素pix）
     *
     * @param resid
     * @return
     */
    public static final float getDimens(int resid) {
        return  BasicApp.getAppContext().getResources().getDimension(resid);
    }

    /**
     * 根据资源id返回字符串数组
     *
     * @param resid
     * @return
     */
    public static final String[] getStringArray(int resid) {
        return BasicApp.getAppContext().getResources().getStringArray(resid);
    }

    /**
     * 获取资源中的整数数据
     *
     * @param resid
     * @return
     */
    public static final int getInteger(int resid) {
        return BasicApp.getAppContext().getResources().getInteger(resid);
    }

    /**
     * 根据资源名获取资源id
     * @param name 资源名
     * @return
     */
    public static final int getDrawableResId(String name) {
        final Context context = BasicApp.getAppContext();
        return context.getResources().getIdentifier(name, "drawable",
                context.getPackageName());
    }

}
