package com.android.av.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {
   public static final String picFilePath = Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg";
   public static String filePath  = null;
   public static File saveFile(Context context) {
      File folderPic = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
      Date date = new Date(System.currentTimeMillis());
      String name = simpleDateFormat.format(date) + ".jpg";
      File file = null;
      file = new File(folderPic + "/" +name);

      try {
         file.createNewFile();
      } catch (IOException e) {
         e.printStackTrace();
      }
      return file;
   }
}
