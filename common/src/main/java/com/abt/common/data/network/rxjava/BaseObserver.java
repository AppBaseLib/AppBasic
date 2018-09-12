package com.abt.common.data.network.rxjava;

import android.content.Context;
import android.os.NetworkOnMainThreadException;
import android.support.annotation.CallSuper;
import android.text.TextUtils;

import com.abt.basic.logger.LogHelper;
import com.abt.common.data.bean.BaseResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * @author 黄卫旗
 * @description BaseObserver
 * @time 2018/09/07
 */
public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {

    private final String TAG = BaseObserver.class.getSimpleName();
    private Context mContext;
    private static Gson gson = new Gson();
    private Disposable disposable;

    private final int RESPONSE_CODE_OK = 0;       //自定义的业务逻辑，成功返回积极数据
    private final int RESPONSE_FATAL_EOR = -1;    //返回数据失败，严重的错误
    private int errorCode = -1111;
    private String errorMsg = "未知的错误！";

    public BaseObserver(Context mCtx) {
        this.mContext = mCtx;
        //HttpUiTips.showDialog(mContext, null);
    }

    public BaseObserver(Context mCtx, boolean showProgress) {
        this.mContext = mCtx;
        if (showProgress) {
            //HttpUiTips.showDialog(mContext, null);
        }
    }

    /**
     * 根据具体的Api业务逻辑去重写onSuccess方法！
     * @param t
     */
    public abstract void onSuccess(T t);

    /**
     * onFailure是选择性重写，but必须Super！
     */
    @CallSuper
    public void onFailure(int code, String message) {
        if (code == RESPONSE_FATAL_EOR && mContext != null) {
            //HttpUiTips.alertTip(mContext, message, code);
        } else {
            disposeEorCode(message, code);
        }
    }

    /**
     * 简单的把Dialog 处理掉
     */
    @Override
    public final void onComplete() {
//        HttpUiTips.dismissDialog(mContext);
    }

    @Override
    public final void onSubscribe(Disposable d) {
        disposable = d;
    }

    @Override
    public final void onNext(BaseResponse<T> response) {
        // HttpUiTips.dismissDialog(mContext);
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
        if (response.getErrorCode() == RESPONSE_CODE_OK || response.getErrorCode() == 200) {
            // response.getCode() == 200 GOOD LIFE  的API真够奇怪的
            // 这里拦截一下使用测试
            onSuccess(response.getData());
        } else {
            onFailure(response.getErrorCode(), response.getErrorMsg());
        }
    }

    /**
     * 通用异常错误的处理，不能弹出一样的东西出来
     */
    @Override
    public final void onError(Throwable t) {
        LogHelper.e(TAG, "Throwable t:" + t.toString());
        //HttpUiTips.dismissDialog(mContext);
        if (t instanceof HttpException) {
            HttpException httpException = (HttpException) t;
            errorCode = httpException.code();
            errorMsg = httpException.getMessage();
            getErrorMsg(httpException);
        } else if (t instanceof SocketTimeoutException) {  //VPN open
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "服务器响应超时";
        } else if (t instanceof ConnectException) {
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "网络连接异常，请检查网络";
        } else if (t instanceof UnknownHostException) {
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "无法解析主机，请检查网络连接";
        } else if (t instanceof UnknownServiceException) {
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "未知的服务器错误";
        } else if (t instanceof IOException) {   //飞行模式等
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "没有网络，请检查网络连接";
        } else if (t instanceof NetworkOnMainThreadException) {
            //主线程不能网络请求，这个很容易发现
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "主线程不能网络请求";
        } else if (t instanceof RuntimeException) {
            //很多的错误都是extends RuntimeException
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "运行时错误" + t.toString();
        }
        onFailure(errorCode, errorMsg);
    }

    /**
     * 对通用问题的统一拦截处理
     */
    private final void disposeEorCode(String message, int code) {
        switch (code) {
            case 101:
            case 112:
            case 123:
            case 401:
                if (mContext != null) { // 退回到登录页面
                    // 应用内简单的跳转(通过URL跳转在'进阶用法'中)
                    // ARouter.getInstance().build("/login/activity").navigation();
                }
                break;
        }
    }

    /**
     * 获取详细的错误的信息 errorCode,errorMsg
     * 以登录的时候的Grant_type 故意写错为例子,这个时候的http 应该是直接的返回401=httpException.code()
     * 但是是怎么导致401的？我们的服务器会在response.errorBody 中的content 中说明
     */
    private final void getErrorMsg(HttpException httpException) {
        String errorBodyStr = "";
        /*try {
            // 我们的项目需要的UniCode转码
            errorBodyStr = TextConvertUtils.convertUnicode(httpException.response().errorBody().string());
        } catch (IOException ioe) {
            Log.e("errorBodyStr ioe:", ioe.toString());
        }*/

        if (TextUtils.isEmpty(errorBodyStr)) {
            return;
        }

        try {
            BaseResponse errorResponse = gson.fromJson(errorBodyStr, BaseResponse.class);
            if (null != errorResponse) {
                errorCode = errorResponse.getErrorCode();
                errorMsg = errorResponse.getErrorMsg();
            }
        } catch (Exception jsonException) {
            jsonException.printStackTrace();
        }

    }

}

