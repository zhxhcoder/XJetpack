package com.zhxh.xjetpack.app.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zhxh.xjetpack.JniCall
import com.zhxh.xjetpack.R
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * Created by zhxh on 2018/8/9
 */

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Example of a call to a native method
        sample_text.text = JniCall.instance.stringFromJNI()

        val intent = Intent(this, MainActivity::class.java)

        sample_text.setOnClickListener { v -> startActivity(intent) }
    }

}
