package com.weetgo.ktcam01

import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import android.Manifest.permission.CAMERA

class MainActivity : AppCompatActivity() {

    // kotlin 没有static成员，使用伴生对象来实现.
    companion object {
        // 下面数值没有实际特殊含义，可随意定义.
        const val REQUEST_IMAGE_CAPTURE = 100
        // 下面数值没有实际数值意义，可任意定义整数，主要用于onRequestPermissionsResult()中进行识别.
        const val PERMISSION_REQUEST_CODE = 102

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 对于使用硬件，权限部分很重要。除下下面的check/requet权限外，要在manifest.xml文件中增加相对应内容.
        if (checkPermissions()) {
            takePicture()
        } else {
            requestPermissions()
        }
    }

    /**
     * 启动camera预览.
     */
    private fun takePicture() {
        // 直接切换到系统的Camera模块，进行显示.
        val inten = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(inten, REQUEST_IMAGE_CAPTURE)
        // 下面一行是典型错误：inten写成intent，而实际上Activity内部包含intent
//        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }

    private fun checkPermissions() : Boolean{
        // 检查使用camera的权限
        return (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED )
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this, arrayOf(CAMERA), PERMISSION_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                 if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    takePicture()
                } else {
                    Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show()
                }
                return
            }
            else -> { }
        }
    }
}
