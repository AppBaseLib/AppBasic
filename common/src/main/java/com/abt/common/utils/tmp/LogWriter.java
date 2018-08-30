package com.abt.common.utils.tmp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogWriter {
	
	private static LogWriter mLogWriter;
	private static String mPath;
	private static Writer mWriter;
	private static SimpleDateFormat mSDF = new SimpleDateFormat("[yy-MM-dd hh:mm:ss]: ");
	
	public LogWriter() {}
	
	private LogWriter(String filePath) {
		LogWriter.mPath = filePath;
		LogWriter.mWriter = null;
	}

	public static LogWriter open(String filePath) {
		if (mLogWriter == null) mLogWriter = new LogWriter(filePath);
		try {
			mWriter = new BufferedWriter(new FileWriter(mPath, true), 2048);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mLogWriter;
	}
	
	public void close() {
		try {
			mWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void print(String log) {
		try {
			if (mWriter == null) return;
			mWriter.write(mSDF.format(new Date()));
			mWriter.write(log);
			mWriter.write("\n");
			mWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void print(Class<?> cls, String log) {
		try {
			mWriter.write(mSDF.format(new Date()));
			mWriter.write(cls.getSimpleName() + " ");
			mWriter.write(log);
			mWriter.write("\n");
			mWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
