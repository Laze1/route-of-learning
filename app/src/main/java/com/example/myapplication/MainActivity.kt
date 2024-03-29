package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ext.inflate
import com.example.myapplication.ext.onClick
import com.example.myapplication.ext.startActivity
import com.example.myapplication.flow.FlowActivity
import com.example.myapplication.hilt.HiltActivity
import com.example.myapplication.jni.JNIActivity
import com.example.myapplication.observer.EventBus
import com.example.myapplication.observer.EventBusActivity
import com.example.myapplication.observer.Subscriber
import com.example.myapplication.paging.PagingActivity
import com.example.myapplication.sqlite.SQLiteActivity
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
            btnHilt.onClick {
                startActivity(HiltActivity::class.java)
            }
            btnUrl.onClick {
                goPage()
            }
            btnJNI.onClick {
                startActivity(JNIActivity::class.java)
            }
            btnSQLite.onClick {
                startActivity(SQLiteActivity::class.java)
            }
        }
    }

    private fun goPage() {
//        Intent().apply {
//            val uri = Uri.parse("paraches://decorelink/share?key=10086")
//            action = "android.intent.action.VIEW"
//            data = uri
//            startActivity(this)
//        }
        Intent().apply {
            val uri = Uri.parse("learnmyapp://host/welcome?key=10088")
            action = "android.intent.action.VIEW"
            data = uri
            startActivity(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.unregister(String::class.java,subscriber)
    }

}
