package com.mihua.frameproject.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.mihua.code.utils.ToastUtils
import com.mihua.frameproject.R

class LuncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_luncher)

        val imageView = findViewById(R.id.imageView2) as ImageView;

        imageView.setOnClickListener {

            ToastUtils.showShortToast("点击图片");
        }

    }



}
