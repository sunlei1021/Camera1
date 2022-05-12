package com.android.native_render;

public class NativeLib {

    // Used to load the 'native_render' library on application startup.
    static {
        System.loadLibrary("native_render");
    }

    /**
     * A native method that is implemented by the 'native_render' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}