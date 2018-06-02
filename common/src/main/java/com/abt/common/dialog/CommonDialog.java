package com.abt.common.dialog;

import android.annotation.SuppressLint;

import com.abt.basic.arch.mvvm.view.dialog.BaseDialog;
import com.abt.basic.arch.mvvm.view.dialog.DialogViewModel;
import com.abt.common.R;
import com.abt.common.util.ResourceUtil;

/**
 * @描述：     @业务层对话框
 * @作者：     @黄卫旗
 * @创建时间： @21/05/2018
 */
@SuppressLint("ValidFragment")
public class CommonDialog extends BaseDialog<CommonDialogViewModel> {

    @SuppressLint("ValidFragment")
    public CommonDialog(int variableId, CommonDialogViewModel model) {
        super(STYLE_NO_TITLE, R.style.Dialog_Theme, R.layout.dialog_common, variableId, model);
    }

    public CommonDialog() {
    }

    /**
     * 构建者创建dialog
     */
    public static final class Builder {
        private int mVariableId;
        private String mPositiveName;
        private String mNegativeName;
        private String mNeutralName;
        private String mContent;
        private DialogListener mDialogListener;

        public Builder(int variableId) {
            this.mVariableId = variableId;
        }

        public final Builder setPositiveName(String positiveName) {
            this.mPositiveName = positiveName;
            return this;
        }

        public final Builder setPositiveName(int resid) {
            this.mPositiveName = ResourceUtil.getString(resid);
            return this;
        }

        public final Builder setNegativeName(String negativeName) {
            this.mNegativeName = negativeName;
            return this;
        }

        public final Builder setNegativeName(int resid) {
            this.mNegativeName = ResourceUtil.getString(resid);
            return this;
        }

        public final Builder setNeutralName(String cancelName) {
            this.mNeutralName = cancelName;
            return this;
        }

        public final Builder setNeutralName(int resid) {
            this.mNeutralName = ResourceUtil.getString(resid);
            return this;
        }

        public final Builder setContent(String content) {
            this.mContent = content;
            return this;
        }

        public final Builder setContent(int resid) {
            this.mContent = ResourceUtil.getString(resid);
            return this;
        }

        public final Builder setDialogListener(DialogListener listener) {
            this.mDialogListener = listener;
            return this;
        }

        public final CommonDialog create() {
            final CommonDialogViewModel model = new CommonDialogViewModel(
                    this.mPositiveName, this.mNegativeName,
                    this.mNeutralName, this.mContent, this.mDialogListener);
            return new CommonDialog(this.mVariableId, model);
        }
    }

    public interface DialogListener {
        public void onPositive(DialogViewModel vm);

        public void onNegative(DialogViewModel vm);

        public void onNeutral(DialogViewModel vm);
    }

}
