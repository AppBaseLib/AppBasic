package com.abt.common.util.tmp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;

public class CallPhoneUtil {

	public static void callPhone(Activity cx, String phoneNum) {
		if (phoneNum != null || phoneNum.length() > 1) {
			Uri uri = Uri.parse("tel:" + phoneNum);
			if (ActivityCompat.checkSelfPermission(cx, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
				// TODO: Consider calling
				//    ActivityCompat#requestPermissions
				// here to request the missing permissions, and then overriding
				//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
				//                                          int[] grantResults)
				// to handle the case where the user grants the permission. See the documentation
				// for ActivityCompat#requestPermissions for more details.
				return;
			}
			cx.startActivity(new Intent(Intent.ACTION_CALL, uri));
		}
	}
	
}