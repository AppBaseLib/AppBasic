package com.abt.basic.arch.mvvm.view;

import android.support.v4.app.Fragment;

import com.abt.basic.arch.mvvm.viewmodel.IViewModel;

/**
 * @描述： @MVVM BaseFragment基类
 * @作者： @黄卫旗
 * @创建时间： @21/05/2018
 */
public class BaseFragment<VM extends IViewModel, TM> extends Fragment implements IView<VM, TM> {

    protected VM mViewModel;
    protected TM mToolbarModel;

    @Override
    public void setViewModel(VM viewModel) {
        this.mViewModel = viewModel;
    }

    @Override
    public void setToolbarViewModel(TM viewModel) {
        mToolbarModel = viewModel;
    }
}
