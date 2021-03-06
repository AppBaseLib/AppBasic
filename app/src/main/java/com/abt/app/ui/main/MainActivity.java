package com.abt.app.ui.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.abt.app.R;
import com.abt.app.app.MainConstant;
import com.abt.app.databinding.ActivityMainBinding;
import com.abt.app.ui.adapter.NewsAdapter;
import com.abt.app.viewmodel.NewsVM;
import com.abt.basic.arch.mvvm.viewmodel.IViewModel;
import com.abt.basic.utils.ToastUtil;
import com.abt.common.helper.DialogHelper;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * @描述： @MainActivity
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public class MainActivity extends AppCompatActivity implements IMainView,
        XRecyclerView.LoadingListener {

    private Context mContext;
    private ActivityMainBinding binding;
    private NewsAdapter newsAdapter; //新闻列表的适配器
    private NewsVM newsVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mContext = this;
        initRecyclerView();
        newsVM = new NewsVM(this, newsAdapter);
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        binding.newsRv.setRefreshProgressStyle(ProgressStyle.BallClipRotate); //设置下拉刷新的样式
        binding.newsRv.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate); //设置上拉加载更多的样式
        binding.newsRv.setArrowImageView(R.mipmap.pull_down_arrow);
        binding.newsRv.setLoadingListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.newsRv.setLayoutManager(layoutManager);
        newsAdapter = new NewsAdapter(this);
        binding.newsRv.setAdapter(newsAdapter);
    }

    @Override
    public void onRefresh() {
        //下拉刷新
        newsVM.loadRefreshData();
    }

    @Override
    public void onLoadMore() {
        //上拉加载更多
        newsVM.loadMoreData();
    }

    @Override
    public void loadStart(int loadType) {
        if (loadType == MainConstant.LoadData.FIRST_LOAD) {
            DialogHelper.getInstance().show(mContext, "加载中...");
        }
    }

    @Override
    public void loadComplete() {
        DialogHelper.getInstance().close();
        binding.newsRv.loadMoreComplete(); //结束加载
        binding.newsRv.refreshComplete(); //结束刷新
    }

    @Override
    public void loadFailure(String message) {
        DialogHelper.getInstance().close();
        binding.newsRv.loadMoreComplete(); //结束加载
        binding.newsRv.refreshComplete(); //结束刷新
        ToastUtil.show(message);
    }

    @Override
    public void setViewModel(IViewModel viewModel) {

    }

    @Override
    public void setToolbarViewModel(Object viewModel) {

    }
}
