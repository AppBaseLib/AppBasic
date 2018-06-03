package com.abt.app.view.sample;

import android.databinding.BaseObservable;
import android.support.annotation.NonNull;

import com.abt.basic.arch.mvvm.view.BaseActivity;
import com.abt.basic.arch.mvvm.view.BaseFragment;

public class SampleActivity extends BaseActivity {

    @NonNull
    @Override
    protected BaseFragment createFragment() {
        return SampleFragment.newInstance();
    }

    @NonNull
    @Override
    protected BaseObservable createViewModel() {
        return new SampleViewModel();
    }
}
