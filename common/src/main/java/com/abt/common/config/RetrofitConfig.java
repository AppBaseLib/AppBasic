package com.abt.common.config;

/**
 * @描述： @RetrofitConfig
 * @作者： @黄卫旗
 * @创建时间： @13/09/2018
 */
public class RetrofitConfig {

    public static final int CACHE_SIZE = 1024*1024*50;// 50M
    public static final int MAX_STALE = 60*60*24*7*4;// 4周
    public static final String CACHE_NAME = "retrofit_cache";

    public static final int CONNECT_TIME_OUT = 15;// 15秒
    public static final int READ_TIME_OUT = 20;// 20秒
    public static final int WRITE_TIME_OUT = 20;// 20秒


}
