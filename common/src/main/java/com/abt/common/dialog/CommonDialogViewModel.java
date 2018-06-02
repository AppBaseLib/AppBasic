package com.abt.common.dialog;

import com.abt.basic.arch.mvvm.view.dialog.DialogViewModel;

/**
 * @描述：     @业务层对话框VM
 * @作者：     @黄卫旗
 * @创建时间： @21/05/2018
 */
public class CommonDialogViewModel extends DialogViewModel {

    public String positiveName;
    public String negativeName;
    public String neutralName;
    public String content;

    private CommonDialog.DialogListener mDialogListener;

    public CommonDialogViewModel(String positiveName,
                                 String negativeName,
                                 String neutralName,
                                 String content,
                                 CommonDialog.DialogListener dialogListener) {
        this.positiveName = positiveName;
        this.negativeName = negativeName;
        this.neutralName = neutralName;
        this.content = content;
        this.mDialogListener = dialogListener;
    }

    public final void onPositiveClick() {
        if (null != mDialogListener) {
            mDialogListener.onPositive(this);
        }
    }

    public final void onNegativeClick() {
        if (null != mDialogListener) {
            mDialogListener.onNegative(this);
        }
    }

    public final void onNeutralClick() {
        if (null != mDialogListener) {
            mDialogListener.onNeutral(this);
        }
    }

    public final void onOutSideClick() {
        //dismiss();
    }

}
