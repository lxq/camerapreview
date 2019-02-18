# camerapreview
A sample for Camera Preview with kotlin in Android.
## 目标
* Android 中添加Camera的权限支持
* 直接调用系统Camera模块进行预览
## 步骤
1. 使用Android Studio 建立空Activity项目
2. Androidmanefest.xml文件中在 <application/\> 前加入如下内容：
``` xml
    <uses-permission android:name="android.permission.CAMERA"/>
    <uses-permission android:name="android.permission.RECORD_AUDIO"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```
3. 权限检测方法:
``` kotlin
    private fun checkPermissions() : Boolean{
        // 检查使用camera的权限
        return (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED )
    }
```
4. 权限请求方法：
``` kotlin
    // private val PERMISSION_REQUEST_CODE = 101
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this, arrayOf(CAMERA), PERMISSION_REQUEST_CODE)
    }
```
5. 调用Camera模块：
``` kotlin
    private fun takePicture() {
        // 直接切换到系统的Camera模块，进行显示.
        val inten = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(inten, REQUEST_IMAGE_CAPTURE)
        // 下面一行是典型错误：inten写成intent，而实际上Activity内部包含intent
        // startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }
```