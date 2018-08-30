package com.abt.common.data.preference;

import android.content.Context;

/**
 * 内存存储，如果只需存内存，直接重写具体方法即可
 */
public class MemoPrefsImpl extends FilePrefsImpl {

	private static PrefsHelper mPre;

	protected MemoPrefsImpl(Context context) {
		super(context);		
	}

	public static synchronized final PrefsHelper getInstance(Context context) {
		if (mPre == null) {
			mPre = new MemoPrefsImpl(context.getApplicationContext());
		}
		return mPre;
	}

}
