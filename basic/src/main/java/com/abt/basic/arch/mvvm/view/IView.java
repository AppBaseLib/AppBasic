package com.abt.basic.arch.mvvm.view;

import com.abt.basic.arch.mvvm.viewmodel.IViewModel;

/**
 * @描述： @V与VM关联接口
 * @作者： @黄卫旗
 * @创建时间： @21/05/2018
 */
public interface IView<VM extends IViewModel, TM> {
    void setViewModel(VM viewModel);
    void setToolbarViewModel(TM viewModel);
}
