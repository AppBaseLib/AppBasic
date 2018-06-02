package com.abt.basic.arch.mvvm.viewmodel;

import com.abt.basic.arch.mvvm.view.INavigator;

/**
 * @描述： @ViewModel业务接口
 * @作者： @黄卫旗
 * @创建时间： @21/05/2018
 */
public interface IViewModel<N extends INavigator> {
    void initialize();
    void setNavigator(N navigator);
}
