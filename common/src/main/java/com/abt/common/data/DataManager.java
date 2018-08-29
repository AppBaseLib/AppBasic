package com.abt.common.data;

import android.util.Log;

public class DataManager {

	private static final String TAG = DataManager.class.getSimpleName();
	private DataManager mManager;
	
	private FileHelpera mFileHelper;

	public DataManager getInstance() {
		if (mManager == null) {
			mManager = new DataManager();
		}
		return mManager;
	}
	
	public void init() {
		mFileHelper = new FileHelpera();
	}
	
	public void test() {
		Log.d(TAG, mFileHelper.toString());
	}
}
