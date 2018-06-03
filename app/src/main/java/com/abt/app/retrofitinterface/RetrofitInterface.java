package com.abt.app.retrofitinterface;

import com.abt.app.bean.NewsBean;
import com.abt.app.constant.URLConstant;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @描述： @RetrofitInterface
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public interface RetrofitInterface {

    //获取“分类中搜索商品”的数据
    @GET(URLConstant.URL_PATH)
    Observable<NewsBean> getNewsData();
}
