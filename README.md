# Camera1
# 参考 github：https://github.com/KnightAndroid/Camera1Java
#工作以来 最先接触Camera2 很多公司依然使用 Camera1 在此参考上述项目 整理 Camera1 API 

Camera1 相关权限
申请Camera权限
<uses-permission android:name="android.permission.CAMERA"/>

需要相机的某一功能
<!--使用uses-feature指定需要相机资源-->
<uses-feature android:name="android.hardware.Camera"/>
<!--需要自动聚焦 -->
<uses-feature android:name="android.hardware.camera.autofocus"/>

Camera1 开发的过程

* 检测相机资源，如果存在相机资源，就请求访问相机资源，否则就结束
* 创建预览界面，一般是继承SurfaceView并且实现SurfaceHolder接口的拍摄预览类，并且创建布局文件，将预览界面和用户界面绑定，进行实时显示相机预览图像
* 创建拍照监听器来响应用户的不同操作，如开始拍照，停止拍照等
* 拍照成功后保存文件，将拍摄获得的图像文件转成位图文件，并且保存输出需要的格式图片
* 释放相机资源，当相机不再使用时，进行释放

需求：
1 调用系统相机拍照并插入系统相册
2 调用系统相册
3 自定义相机
3.1
3.2
3.3