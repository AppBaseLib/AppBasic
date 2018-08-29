package com.abt.common.config;

import android.os.Environment;

import com.abt.common.util.FileUtil;

import java.io.File;

/**
 * @描述： @PathConfig
 * @作者： @黄卫旗
 * @创建时间： @29/08/2018
 */
public class PathConfig {

    public static final String CAMERA_DIR_NAME = "Camera";
    public static final String THUMB_DIR_NAME = "Thumb";
    public static final String CACHE_DIR_NAME = "Cache";

    private static final String DCIM = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DCIM) + File.separator;

    private static final String ALBUM_PATH = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DCIM)
            + File.separator + CAMERA_DIR_NAME + File.separator;

    private static final String THUMB_PATH = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DCIM)
            + File.separator + THUMB_DIR_NAME + File.separator;

    private static final String CACHE_PATH = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DCIM)
            + File.separator + CACHE_DIR_NAME + File.separator;

    /**
     * 创建相册路径
     */
    public final static void makedirs() {
        //创建相册路径
        FileUtil.createPath(ALBUM_PATH);
        //创建缩略图目录
        FileUtil.createPath(THUMB_PATH);
        //创建缓存图目录
        FileUtil.createPath(CACHE_DIR_NAME);
    }

    /**
     * 得到相册路径
     * @return
     */
    public final static String getAlbumPath() {
        return ALBUM_PATH;
    }

    public final static String getThumbPath() {
        return THUMB_PATH;
    }

    public final static String getCachePath() {
        return CACHE_PATH;
    }
}
