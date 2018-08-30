package com.abt.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import com.abt.common.global.GlobalConstant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileUtil<T> {

	private static final String TAG = FileUtil.class.getSimpleName();
	
	public static void saveBitmap(Bitmap bm, String picName) {
		Log.d(TAG, "保存图片");
		try {
			if (!isFileExist("")) {
				createSDDir("");
			}
			File f = new File(GlobalConstant.FORMATS_PATH, picName + ".JPEG");
			if (f.exists()) {
				f.delete();
			}
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
			Log.d(TAG, "已经保存");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File getFile(String fileName) throws IOException {
		File file = new File(GlobalConstant.FORMATS_PATH + fileName);
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			System.out.println("create File :" + file.getAbsolutePath());
			boolean boo = file.createNewFile();
			System.out.println("create File :" + boo);
		}
		return file;
	}

	public static File getFilePath(String filePath, String fileName) {
		File file = null;
		filePath = GlobalConstant.FORMATS_PATH+filePath;
		makeRootDirectory(filePath);
		try {
			file = new File(filePath + fileName+".txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}

	public static void makeRootDirectory(String filePath) {
		File file = null;
		try {
			file = new File(filePath);
			if (!file.exists()) {
				file.mkdir();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static File createSDDir(String dirName) throws IOException {
		File dir = new File(GlobalConstant.FORMATS_PATH + dirName);
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			System.out.println("createSDDir:" + dir.getAbsolutePath());
			System.out.println("createSDDir:" + dir.mkdir());
		}
		return dir;
	}

	public static boolean isFileExist(String fileName) {
		File file = new File(GlobalConstant.FORMATS_PATH + fileName);
		file.isFile();
		return file.exists();
	}

	public static void delFile(String fileName) {
		File file = new File(GlobalConstant.FORMATS_PATH + fileName);
		if (file.isFile()) {
			file.delete();
		}
		file.exists();
	}

	public static void deleteDir() {
		File dir = new File(GlobalConstant.FORMATS_PATH);
		if (dir == null || !dir.exists() || !dir.isDirectory())
			return;

		for (File file : dir.listFiles()) {
			if (file.isFile())
				file.delete(); // 删除所有文件
			else if (file.isDirectory())
				deleteDir(); // 递规的方式删除文件夹
		}
		dir.delete();// 删除目录本身
	}

	public static boolean fileIsExists(String path) {
		try {
			File f = new File(path);
			if (!f.exists()) {
				return false;
			}
		} catch (Exception e) {

			return false;
		}
		return true;
	}

	public String getString(String s) {
		String path = null;
		if (s == null)
			return "";
		for (int i = s.length() - 1; i > 0; i++) {
			s.charAt(i);
		}
		return path;
	}

	public static void createPath(String path) {
		File file = new File(path);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		if (!file.exists()) {
			file.mkdir();
		}
	}

	/**
	 * 把list数据写入到文件
	 * @param context
	 * @param fileName
	 * @param list
	 */
	public void write(Context context, String fileName, List<T> list) {
		ObjectOutputStream objOutStream = null;
		FileOutputStream fileOutStream = null;
		try {
			fileOutStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			objOutStream = new ObjectOutputStream(fileOutStream);
			objOutStream.writeObject(list);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
		}

		if (objOutStream != null) {
			try {
				objOutStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (fileOutStream != null) {
			try {
				fileOutStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 把list数据从文件中读出来
	 * @param context
	 * @param fileName
	 * @return
	 */
	public ArrayList<T> read(Context context, String fileName) {
		ObjectInputStream objInStream = null;
		FileInputStream fileInStream = null;
		ArrayList<T> savedArrayList = new ArrayList<T>();
		try {
			fileInStream = context.openFileInput(fileName);
			objInStream = new ObjectInputStream(fileInStream);
			savedArrayList = (ArrayList<T>) objInStream.readObject();
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
		}
		return savedArrayList;
	}

	/**
	 * It store the file data into the file given by the filename
	 *
	 * @param context  the activity context
	 * @param fileName the filename to store in
	 * @param fileData the string to be stored
	 */
	public static void writeStr(Context context, String fileName, String fileData) {
		try {
			FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			fos.write(fileData.getBytes());
			fos.close();
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
		}
	}

	/**
	 * Reads the data stored in the file given by the filename
	 *
	 * @param context  the activity context
	 * @param fileName the name of the filename
	 * @return the string read
	 */
	public static String readStr(Context context, String fileName) {
		try {
			FileInputStream fis = context.openFileInput(fileName);
			StringBuilder builder = new StringBuilder();
			int ch;
			while ((ch = fis.read()) != -1) {
				builder.append((char) ch);
			}
			return builder.toString();
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
		}
		return "";
	}
}
