package com.abt.common.utils.tmp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Base64;

import com.abt.common.R;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class CommonUtil {
	
	public static SimpleDateFormat getFormat(String partten) {
		return new SimpleDateFormat(partten);
	}

	public static String getTimeDisplay(String getDateString, Context context) {
		Date date = null;
		try {
			date = getFormat("yyyy-MM-dd HH:mm:ss").parse(getDateString);
		} catch (ParseException e) {
			date = new Date();
		}

		final long getTime = date.getTime();
		final long currTime = System.currentTimeMillis();
		final Date formatSysDate = new Date(currTime);

		// 判断当前总天数
		final int sysMonth = formatSysDate.getMonth() + 1;
		final int sysYear = formatSysDate.getYear();

		// 计算服务器返回时间与当前时间差值
		final long seconds = (currTime - getTime) / 1000;
		final long minute = seconds / 60;
		final long hours = minute / 60;
		final long day = hours / 24;
		final long month = day / calculateDaysOfMonth(sysYear, sysMonth);
		final long year = month / 12;

		if (year > 0 || month > 0 || day > 0) {
			final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			return simpleDateFormat.format(date);
		} else if (hours == 1) {
			return hours + " " + context.getString(R.string.str_hourago);
		} else if (hours > 1) {
			return hours + " " + context.getString(R.string.str_hoursago);
		} else if (minute == 1) {
			return minute + " " + context.getString(R.string.str_minago);
		} else if (minute > 1) {
			return minute + " " + context.getString(R.string.str_minsago);
		} else if (seconds > 0) {
			return "1 " + context.getString(R.string.str_minago);
		} else {
			return "1 " + context.getString(R.string.str_minago);
		}
	}

	private static int calculateDaysOfMonth(int year, int month) {
		int day = 0;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			day = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			day = 30;
			break;
		case 2:
			day = year % 100 == 0 ? year % 400 == 0 ? 29 : 28
					: year % 4 == 0 ? 29 : 28;
			break;
		}
		return day;
	}
	
	int isYeapYear(int year) {  
	    if ((0 == year % 4 && 0 != year % 100) || (0 == year % 400))  
	        return 1;  
	    return 0;  
	}
	  
	int monthdyas[][] = {  
		{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}, 
		{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}  
	};
	  
	//获取year年month月的天数  
	int GetMonthDays(int year, int month) {  
	    return monthdyas[isYeapYear(year)][month - 1];  
	}
	
	// private static String mYear;
	private static String mMonth;
	private static String mDay;
	private static String mWay;
	
	public static List<String> getDateList() {
		final Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		// mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
		mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
		mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
		mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
		if ("1".equals(mMonth)) {
			mMonth = "January";
		} else if ("2".equals(mMonth)) {
			mMonth = "February";
		} else if ("3".equals(mMonth)) {
			mMonth = "March";
		} else if ("4".equals(mMonth)) {
			mMonth = "April";
		} else if ("5".equals(mMonth)) {
			mMonth = "May";
		} else if ("6".equals(mMonth)) {
			mMonth = "June";
		} else if ("7".equals(mMonth)) {
			mMonth = "July";
		} else if ("8".equals(mMonth)) {
			mMonth = "August";
		} else if ("9".equals(mMonth)) {
			mMonth = "September";
		} else if ("10".equals(mMonth)) {
			mMonth = "October";
		} else if ("11".equals(mMonth)) {
			mMonth = "November";
		} else if ("12".equals(mMonth)) {
			mMonth = "December";
		}

		if ("1".equals(mWay)) {
			mWay = "Sun";
		} else if ("2".equals(mWay)) {
			mWay = "Mon";
		} else if ("3".equals(mWay)) {
			mWay = "Tus";
		} else if ("4".equals(mWay)) {
			mWay = "Wed";
		} else if ("5".equals(mWay)) {
			mWay = "Thu";
		} else if ("6".equals(mWay)) {
			mWay = "Fri";
		} else if ("7".equals(mWay)) {
			mWay = "Sat";
		}
		List<String> list = new ArrayList<String>();
		list.add(mMonth);
		list.add(mDay);
		list.add(mWay);
		return list;
	}

	public static Bitmap bytes2Bimap(byte[] b) {
		if (b.length != 0) {
			return BitmapFactory.decodeByteArray(b, 0, b.length);
		} else {
			return null;
		}
	}

	public static byte[] bitmap2Bytes(Bitmap bm) {
		if (bm == null) return null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}

	public static String bytes2String(byte[] bytes) {
		if (bytes == null) return null;
		return Base64.encodeToString(bytes, Base64.DEFAULT);
	}

	public static byte[] string2Bytes(String str) {
		if (str == null) return null;
		return Base64.decode(str, Base64.DEFAULT);
	}

	public static Bitmap drawableToBitmap(Drawable drawable) {
		if (drawable == null) return null;
		int w = drawable.getIntrinsicWidth();
		int h = drawable.getIntrinsicHeight();

		Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
				: Bitmap.Config.RGB_565;
		Bitmap bitmap = Bitmap.createBitmap(w, h, config);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, w, h);
		drawable.draw(canvas);
		return bitmap;
	}

	/**
	 * Convent drawable id to Bitmap
	 */
	public static Bitmap drawableToBitmap(Resources res, int drawable_id) {
		if (res == null && drawable_id <= 0) return null;

		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inScaled = false;
		opts.inJustDecodeBounds = false;
		opts.inSampleSize = 1;
		return BitmapFactory.decodeResource(res, drawable_id, opts);
	}

	public static Bitmap drawableToBitmap(Resources res, int drawable_id,
			BitmapFactory.Options opts) {
		if (res == null && drawable_id <= 0) return null;
		return BitmapFactory.decodeResource(res, drawable_id, opts);
	}
	
	/**
	 * Convent drawable id to Base64 encode String
	 */
	public static String drawableToString(Resources res, int drawable_id) {
		if (res == null && drawable_id <= 0) return null;
		return bytes2String(bitmap2Bytes(drawableToBitmap(res, drawable_id)));
	}
	
	public static Bitmap toRoundCorner(Bitmap bitmap, float ratio) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		canvas.drawRoundRect(rectF, bitmap.getWidth() / ratio, bitmap.getHeight() / ratio, paint);

		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}
    
	/**
	 * 图片转成string
	 * @param bitmap
	 * @return String
	 */
	public static String bitmapToString(Bitmap bitmap) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.PNG, 100, baos);
		byte[] appicon = baos.toByteArray();
		return Base64.encodeToString(appicon, Base64.DEFAULT);
	}

	/**
	 * string转成bitmap
	 * @param st
	 */
	public static Bitmap stringToBitmap(String st) {
		try {
			byte[] bitmapArray;
			bitmapArray = Base64.decode(st, Base64.DEFAULT);
			Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
			return bitmap;
		} catch (Exception e) {
			return null;
		}
	}
	
	// 压缩图片到指定尺寸
	public static Bitmap decodeSampledBitmapFromBytes(byte[] by, int reqWidth, int reqHeight) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
	    Bitmap map = BitmapFactory.decodeByteArray(by, 0, by.length, options);
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);  
	    options.inJustDecodeBounds = false;
	    map = BitmapFactory.decodeByteArray(by, 0, by.length, options);
		return map;
	} 
	
    public static int calculateInSampleSize(BitmapFactory.Options options,
            int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }
}
