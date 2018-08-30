package com.abt.common.data.preference;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

/**
 * 界面调用：<br>
 * PrefsHelper preferences = PrefsHelper.Factory.getInstance(this); <br>
 * String accountId = preferences.getAccountId();
 */
public interface PrefsHelper {

	public SharedPreferences getSharedPreferences();

	/**
	 * 获取保存的数据
	 */
	public <T> T get(Context context, String key, Class<T> c);

	/**
	 * 保存数据，持久化
	 */
	public boolean save(Context context, String key, Serializable obj);

	public void setAccountId(String accountId);

	public String getAccountId();

	public String getToken();

	public void setToken(String token);

	public static final class Factory {
		public static final PrefsHelper getInstance(Context context) {
			return MemoPrefsImpl.getInstance(context);
		}
	}
}
