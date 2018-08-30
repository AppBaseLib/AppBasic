package com.abt.common.data.network;

/**
 * @描述： @HttpHelper
 * @作者： @黄卫旗
 * @创建时间： @29/08/2018
 */
public interface HttpHelper {

    void login(String username, String password);

    void register(String username, String password, String repassword);
}
