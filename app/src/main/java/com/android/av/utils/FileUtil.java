package com.android.av.utils;

import android.net.Uri;
import android.os.Environment;

public class FileUtil {
   public static final String picFilePath = Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg";


}
