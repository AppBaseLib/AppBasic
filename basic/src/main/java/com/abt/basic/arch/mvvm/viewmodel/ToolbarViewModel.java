package com.abt.basic.arch.mvvm.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * @描述： @状态栏VM
 * @作者： @黄卫旗
 * @创建时间： @21/05/2018
 */
public final class ToolbarViewModel extends BaseObservable {

    /**
     * 显示网络状态
     */
    public final ObservableField<Integer> network = new ObservableField<>();
    /**
     * 显示标题
     */
    public final ObservableField<String> title = new ObservableField<>();
    /**
     * 显示时间
     */
    public final ObservableField<String> time = new ObservableField<>();
    /**
     * 电量
     */
    public final ObservableField<Integer> progress = new ObservableField<>();
    /**
     * 是否正在充电
     */
    public final ObservableField<Boolean> isCharge = new ObservableField<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ToolbarViewModel() {
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ToolbarViewModel(int title) {
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void init() {

    }

}
