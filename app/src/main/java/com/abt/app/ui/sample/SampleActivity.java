package com.abt.app.ui.sample;

import android.support.annotation.NonNull;

import com.abt.basic.arch.mvvm.view.BaseActivity;
import com.abt.basic.arch.mvvm.viewmodel.ToolbarViewModel;
import com.abt.app.viewmodel.SampleViewModel;

public class SampleActivity extends BaseActivity<SampleFragment,
        SampleViewModel, ToolbarViewModel> {

    @NonNull
    @Override
    protected SampleFragment createFragment() {
        return SampleFragment.newInstance();
    }

    @NonNull
    @Override
    protected SampleViewModel createViewModel() {
        return new SampleViewModel();
    }
}
