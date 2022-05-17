package com.android.av;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.android.av.utils.FileUtil;
import com.android.av.utils.SystemUtil;
import com.android.av.R;
import com.android.av.utils.Utils;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SurfaceHolder.Callback, Camera.PreviewCallback {

    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;



    //MainActivity UI

    private Button bt_go_to_system_camera;
    private Button bt_go_to_system_gallery;
    private Button bt_go_to_custom_camera;
    private Button bt_go_to_opengl_es;

    private int permissionGranted = 1;

    //权限 主要有 相机 录音 存储
    private static final String[] permissions = {
            "android.permission.CAMERA",
            "android.permission.RECORD_AUDIO",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    //拍照照片的路径
    private File picFile;

    //
    private Intent intentForCamera;

    private Uri uri;

    private final String TAG = "MainActivityAV";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
        checkPermissions();
    }

    private void checkPermissions() {
        if (!checkPermission(permissions)) {
            ActivityCompat.requestPermissions(this,permissions,permissionGranted);
        }
    }

    private void initView() {
        bt_go_to_system_camera = findViewById(R.id.go_to_system_camera);
        bt_go_to_custom_camera = findViewById(R.id.go_to_custom_camera);
        bt_go_to_system_gallery = findViewById(R.id.go_to_system_gallery);
    }

    private boolean checkPermission(String [] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.e(TAG, "onActivityResult requestCode: " + requestCode );
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e(TAG, "onRequestPermissionsResult requestCode : " + requestCode);
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initView();
                } else {
                    showDialog();
                }
                break;
        }
    }


    // 未同意权限 弹出弹窗判断是否 需要设置权限
    // 需要设置 跳转设置界面
    // 不需要设置 直接finish() 推出当前应用

    private void showDialog() {
        AlertDialog alertDialog  = new AlertDialog.Builder(this)
                .setTitle("Warring")
                .setMessage("")
                .setPositiveButton("sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.go_to_custom_camera:

                break;
            case R.id.go_to_system_gallery:

                break;
            case R.id.go_to_system_camera:
                goSystemCamera();

                break;
            default:
                break;

        }
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }


    private void goSystemCamera() {
        picFile = new File(FileUtil.picFilePath);
        intentForCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //android 7.0 以上应用私有目录被限制访问
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ) {
            uri = FileProvider.getUriForFile(this,
                    SystemUtil.getPackageName(getApplicationContext()) + ".fileprovider"
                    ,picFile);
            intentForCamera.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(picFile);
        }


        //指定ACTION为MediaStore.EXTRA_OUTPUT
        intentForCamera.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        //请求码赋值为1
        startActivityForResult(intentForCamera, Utils.REQUEST_CODE_FOR_SYSTEM_CAMERA);
    }


    private void initListener() {
        bt_go_to_custom_camera.setOnClickListener(this);
        bt_go_to_system_gallery.setOnClickListener(this);
        bt_go_to_system_camera.setOnClickListener(this);
    }
}
