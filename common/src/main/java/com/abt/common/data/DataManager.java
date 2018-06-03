package com.abt.common.data;

import android.util.Log;

import com.abt.common.data.file.FileHelper;

public class DataManager {

	private static final String TAG = DataManager.class.getSimpleName();
	private DataManager mManager;
	
	private FileHelper mFileHelper;

	public DataManager getInstance() {
		if (mManager == null) {
			mManager = new DataManager();
		}
		return mManager;
	}
	
	public void init() {
		mFileHelper = new FileHelper();
	}
	
	public void test() {
		Log.d(TAG, mFileHelper.toString());
	}
}
