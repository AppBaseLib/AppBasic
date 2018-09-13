package com.abt.basic.arch.mvvm.view.activity;

import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.abt.basic.R;
import com.abt.basic.arch.mvvm.view.fragment.BaseFragment;
import com.abt.basic.arch.mvvm.view.INavigator;
import com.abt.basic.arch.mvvm.view.holder.ViewModelHolder;
import com.abt.basic.arch.mvvm.viewmodel.IViewModel;

/**
 * @描述： @MVVM Activity基类
 * @作者： @黄卫旗
 * @创建时间： @21/05/2018
 */
public abstract class BaseActivity<V extends BaseFragment<VM, TM>, VM extends BaseObservable & IViewModel, TM>
        extends AppCompatActivity {

    protected static final String FRAGMENT_TAG_VIEW_MODEL = "FRAGMENT_TAG_VIEW_MODEL";

    protected VM mViewModel;
    protected TM mToolbarModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_common);

        V fragment = findOrCreateViewFragment();
        mViewModel = findOrCreateViewModel();
        mToolbarModel = createToolbarViewModel();
        fragment.setViewModel(mViewModel);
        if (null != mToolbarModel) {
            fragment.setToolbarViewModel(mToolbarModel);
        }
    }

    @NonNull
    private V findOrCreateViewFragment() {
        V fragment = (V) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (fragment == null) {
            fragment = createFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fl_content, fragment)
                    .commitAllowingStateLoss();
        }
        return fragment;
    }

    @NonNull
    private VM findOrCreateViewModel() {
        ViewModelHolder<VM> viewModelHolder = (ViewModelHolder<VM>) getSupportFragmentManager()
                .findFragmentByTag(FRAGMENT_TAG_VIEW_MODEL);
        if (viewModelHolder != null && viewModelHolder.getViewModel() != null) {
            // model已经保存，直接返回
            if (this instanceof INavigator) {
                viewModelHolder.getViewModel().setNavigator((INavigator) this);
            }
            return viewModelHolder.getViewModel();
        } else {
            // 还没有ViewModel，创建
            VM viewModel = createViewModel();
            if (this instanceof INavigator) {
                viewModel.setNavigator((INavigator) this);
            }
            viewModelHolder = ViewModelHolder.createContainer(viewModel);
            // 通过FragmentManager绑定Activity生命周期
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(viewModelHolder, FRAGMENT_TAG_VIEW_MODEL)
                    .commit();
            return viewModel;
        }
    }

    /**
     * 创建Fragment
     */
    @NonNull
    protected abstract V createFragment();

    /**
     * 创建ViewModel
     */
    @NonNull
    protected abstract VM createViewModel();

    /**
     * 创建ToolbarViewModel
     */
    protected TM createToolbarViewModel() {
        return null;
    }

}
