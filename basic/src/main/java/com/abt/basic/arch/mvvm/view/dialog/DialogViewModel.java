package com.abt.basic.arch.mvvm.view.dialog;

import android.databinding.BaseObservable;

import java.lang.ref.WeakReference;

/**
 * @描述： @Dialog VM基类
 * @作者： @黄卫旗
 * @创建时间： @21/05/2018
 */
public class DialogViewModel extends BaseObservable {

    private WeakReference<IDialogView> mIDialogView;

    public DialogViewModel() {

    }

    /**
     * 绑定视图
     * @param dialogView
     */
    void bindView(IDialogView dialogView) {
        this.mIDialogView = new WeakReference<>(dialogView);
    }

    /**
     * 解除绑定
     */
    void unbindView() {
        this.mIDialogView.clear();
    }

    /**
     * 关闭对话框
     */
    public final void dismiss() {
        if (null != mIDialogView) {
            final IDialogView dialog = mIDialogView.get();
            if (null != dialog) {
                dialog.hideDialog();
            }
        }
    }
}
