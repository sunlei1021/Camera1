package com.android.av.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class SystemUtil {

   public static String getPackageName(Context context) {
      PackageManager packageManager = context.getPackageManager();
      try {
         PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
         return packageInfo.packageName;
      } catch (PackageManager.NameNotFoundException e) {
         e.printStackTrace();
      }

      return null;

   }
}
