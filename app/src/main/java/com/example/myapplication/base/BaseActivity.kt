package com.example.myapplication.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.myapplication.ext.inflate
import com.example.myapplication.ext.inflateBinding
import kotlin.reflect.KClass

abstract class BaseActivity<Binding : ViewBinding>(
) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

    }
}