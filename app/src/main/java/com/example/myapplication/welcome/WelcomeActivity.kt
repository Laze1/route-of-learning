package com.example.myapplication.welcome

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityHiltBinding
import com.example.myapplication.databinding.ActivityWelcomeBinding
import com.example.myapplication.ext.inflate

class WelcomeActivity : AppCompatActivity() {

    private val binding: ActivityWelcomeBinding by inflate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        val intent = intent
        val scheme = intent.scheme
        val uri = intent.data
        binding.info.text = "$uri"
        if (uri != null) {
            val host = uri.host
            val path = uri.path
            val query = uri.query
            val test1 = uri.getQueryParameter("arg0")//参数

        }
    }
    }


