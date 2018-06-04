package com.abt.common.util.tmp;

import android.util.Log;

public class LogUtil {
	
	private static int DEBUG = 0;

	public static void d(String tag, String msg) {
		if (DEBUG == 1) Log.d(tag, msg);
	}

	public static void e(String tag, String msg) {
		if (DEBUG == 1) Log.e(tag, msg);
	}

	public static void i(String tag, String msg) {
		if (DEBUG == 1) Log.i(tag, msg);
	}

	public static void v(String tag, String msg) {
		if (DEBUG == 1) Log.v(tag, msg);
	}

	public static void w(String tag, String msg) {
		if (DEBUG == 1) Log.w(tag, msg);
	}
	
}
