package com.example.car_message.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * 包信息的工具类
 */
public class PackageInfoUtils {
	/**
	 * 获取应用程序apk包的版本信息
	 *
	 * @param context
	 *            上下文
	 * @return
	 */
	public static String getPackageVersion(Context context) {
		try {
			PackageInfo packinfo = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
			String version = packinfo.versionName;
			return version;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return "解析版本号失败";
		}
	}
}