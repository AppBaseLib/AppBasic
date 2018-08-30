package com.abt.common.data.network;

import com.abt.common.data.network.api.WebApis;

/**
 * @描述： @HttpHelperImpl
 * @作者： @黄卫旗
 * @创建时间： @29/08/2018
 */
public class HttpHelperImpl implements HttpHelper {

    private WebApis mWebApis;

    //@Inject TODO 用Dagger来放开这个注解
    HttpHelperImpl(WebApis webApis) {
        mWebApis = webApis;
    }

    @Override
    public void login(String username, String password) {
        mWebApis.getLoginData(username, password);
    }

    @Override
    public void register(String username, String password, String repassword) {
        mWebApis.getRegisterData(username, password, repassword);
    }

}
