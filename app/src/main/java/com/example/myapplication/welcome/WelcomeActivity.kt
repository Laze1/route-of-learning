package com.example.myapplication.welcome

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityWelcomeBinding
import com.example.myapplication.ext.inflate

class WelcomeActivity : AppCompatActivity() {

    private val binding: ActivityWelcomeBinding by inflate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        val uri = intent.data
        if (uri != null) {
            val host = uri.host
            val path = uri.path
            val query = uri.query
            val key = uri.getQueryParameter("key")//参数
            binding.info.text = "$uri\nhost=$host\npath=$path\nquery=$query\nkey=$key"

            //复制到粘贴板
            val manager: ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val mClipData = ClipData.newPlainText("Label", binding.info.text)
            // 将ClipData内容放到系统剪贴板里。
            manager.setPrimaryClip(mClipData)

        }
    }
}


