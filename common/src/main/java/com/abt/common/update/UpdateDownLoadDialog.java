package com.abt.common.update;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.abt.common.R;

public class UpdateDownLoadDialog extends Dialog {

	private Context context;
	private String cacelButtonText;
	private ClickListenerInterface clickListenerInterface;

	/* 更新进度条 */
	private ProgressBar mProgress;

	public interface ClickListenerInterface {
		void doCancel();
	}

	public UpdateDownLoadDialog(Context context, String cacelButtonText) {
		super(context, R.style.dialog);
		this.context = context;
		this.cacelButtonText = cacelButtonText;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	public ProgressBar getProgressView() {
		return mProgress;
	}

	public void init() {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.update_download_dialog_layout,
				null);
		setContentView(view);
		mProgress = (ProgressBar) view.findViewById(R.id.update_progress);

		TextView tvCancel = (TextView) view
				.findViewById(R.id.filter_dialog_cancel_btn);

		tvCancel.setText(cacelButtonText);
		tvCancel.setOnClickListener(new android.view.View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
				clickListenerInterface.doCancel();
			}
		});

		Window dialogWindow = getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
		lp.height = (int) (d.heightPixels * 0.2);
		lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
		dialogWindow.setAttributes(lp);
	}

	public void setClicklistener(ClickListenerInterface clickListenerInterface) {
		this.clickListenerInterface = clickListenerInterface;
	}

}