package com.abt.basic.arch.mvvm.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * @描述： @ViewModel容器
 * @作者： @黄卫旗
 * @创建时间： @21/05/2018
 */
public class ViewModelHolder<VM> extends Fragment {

    private VM mViewModel;

    /**
     * 调用{@link #createContainer(Object)}创建实例
     */
    public ViewModelHolder() {
    }

    public static <M> ViewModelHolder createContainer(@NonNull M viewModel) {
        ViewModelHolder<M> viewModelContainer = new ViewModelHolder<>();
        viewModelContainer.setViewModel(viewModel);
        return viewModelContainer;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    public VM getViewModel() {
        return mViewModel;
    }

    public void setViewModel(@NonNull VM viewModel) {
        mViewModel = viewModel;
    }

}
