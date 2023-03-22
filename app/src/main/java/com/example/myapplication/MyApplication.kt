package com.example.myapplication

import android.app.Application
import com.example.myapplication.sqlite.ULogDB
import dagger.hilt.android.HiltAndroidApp

/**
 *
 * MyApplication
 * @author Wu Xi
 * @date 2023/1/13 10:29
 *
 *
 */
@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ULogDB.init(this)
    }
}