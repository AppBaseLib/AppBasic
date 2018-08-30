package com.abt.common.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.abt.common.data.db.DbHelper;
import com.abt.common.data.file.FileHelper;
import com.abt.common.data.network.HttpHelper;
import com.abt.common.data.preference.PrefsHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataManager implements HttpHelper, DbHelper, FileHelper, PrefsHelper {

	private static final String TAG = DataManager.class.getSimpleName();

	private HttpHelper mHttpHelper;
	private DbHelper mDBHelper;
	private FileHelper mFileHelper;
	private PrefsHelper mPreHelper;

	public DataManager(HttpHelper httpHelper, DbHelper dbHelper, FileHelper fileHelper,
                       PrefsHelper prefsHelper) {
		mHttpHelper = httpHelper;
		mDBHelper = dbHelper;
		mFileHelper = fileHelper;
		mPreHelper = prefsHelper;
	}

	@Override
	public void get() {
		mDBHelper.get();
	}

	@Override
	public SharedPreferences getSharedPreferences() {
		return getSharedPreferences();
	}

	@Override
	public <T> T get(Context context, String key, Class<T> c) {
		return get(context, key, c);
	}

	@Override
	public boolean save(Context context, String key, Serializable obj) {
		return mPreHelper.save(context, key, obj);
	}

	@Override
	public void setAccountId(String accountId) {
		mPreHelper.setAccountId(accountId);
	}

	@Override
	public String getAccountId() {
		return mPreHelper.getAccountId();
	}

	@Override
	public String getToken() {
		return mPreHelper.getToken();
	}

	@Override
	public void setToken(String token) {
		mPreHelper.setToken(token);
	}

	@Override
	public void login(String username, String password) {
		mHttpHelper.login(username, password);
	}

	@Override
	public void register(String username, String password, String repassword) {
		mHttpHelper.register(username, password, repassword);
	}

	@Override
	public void saveStorage2SDCard(List arrayList, String fileName) {
		mFileHelper.saveStorage2SDCard(arrayList, fileName);
	}

	@Override
	public ArrayList getStorageEntities(String fileName) {
		return mFileHelper.getStorageEntities(fileName);
	}
}
