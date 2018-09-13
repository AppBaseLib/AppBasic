package com.abt.app.ui.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abt.app.databinding.FragmentSampleBinding;
import com.abt.app.viewmodel.SampleViewModel;
import com.abt.basic.arch.mvvm.view.fragment.BaseFragment;
import com.abt.basic.arch.mvvm.viewmodel.ToolbarViewModel;

/**
 * @描述： @SampleFragment
 * @作者： @黄卫旗
 * @创建时间： @2018/5/28
 */
public class SampleFragment extends BaseFragment<SampleViewModel, ToolbarViewModel> {

    private FragmentSampleBinding mFragmentMainBinding;

    /**
     * 返回实例
     */
    public static SampleFragment newInstance() {
        return new SampleFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.initialize();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mFragmentMainBinding = FragmentSampleBinding.inflate(inflater, container, false);
        mFragmentMainBinding.setToolbarVM(mToolbarModel);
        //mFragmentMainBinding.setSampleVM(mViewModel);
        return mFragmentMainBinding.getRoot();
    }

}
