package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ext.inflate
import com.example.myapplication.ext.onClick
import com.example.myapplication.ext.startActivity
import com.example.myapplication.flow.FlowActivity
import com.example.myapplication.observer.EventBus
import com.example.myapplication.observer.EventBusActivity
import com.example.myapplication.observer.Subscriber
import com.example.myapplication.paging.PagingActivity
import com.example.myapplication.workmanager.WorkManagerActivity


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by inflate()

    private val subscriber = object : Subscriber {
        override fun onEvent(event: Any) {
            Log.d("eventBus","收到消息：${event as String}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setClick()


        EventBus.register(String::class.java,subscriber)
    }

    private fun setClick() {
        binding.apply {
            btnWork.onClick {
                startActivity(WorkManagerActivity::class.java)
            }
            btnFlow.onClick {
                startActivity(FlowActivity::class.java)
            }
            btnEventBus.onClick {
                startActivity(EventBusActivity::class.java)
            }
            btnPaging.onClick {
                startActivity(PagingActivity::class.java)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.unregister(String::class.java,subscriber)
    }

}
