package com.example.myapplication.hilt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityHiltBinding
import com.example.myapplication.ext.inflate
import com.example.myapplication.ext.onClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HiltActivity : AppCompatActivity() {

    private val binding: ActivityHiltBinding by inflate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnHilt.onClick {

        }
    }
}