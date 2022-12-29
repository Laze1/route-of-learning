package com.example.myapplication.observer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityEventBusBinding
import com.example.myapplication.ext.inflate

/**
 *
 * EventBusActivity
 * @author Wu Xi
 * @date 2022/12/29 15:55
 *
 */
class EventBusActivity : AppCompatActivity() {

    private val binding: ActivityEventBusBinding by inflate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.post("收到消息了")

    }

}