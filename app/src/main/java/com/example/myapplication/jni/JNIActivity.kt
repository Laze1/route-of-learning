package com.example.myapplication.jni

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityJniactivityBinding
import com.example.myapplication.ext.inflate

class JNIActivity : AppCompatActivity() {

    private val binding: ActivityJniactivityBinding by inflate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jniactivity)

        binding.click.setOnClickListener {
            binding.showText.text = "调用${JNILoader().intFromJNI()}结果：${JNILoader().stringFromJNI()}"
        }
    }

}