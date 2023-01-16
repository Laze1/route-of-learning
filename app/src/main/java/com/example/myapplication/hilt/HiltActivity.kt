package com.example.myapplication.hilt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityHiltBinding
import com.example.myapplication.ext.inflate
import com.example.myapplication.ext.onClick
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HiltActivity : AppCompatActivity() {

    private val binding: ActivityHiltBinding by inflate()

    @Inject
    lateinit var hiltBean: HiltBean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnHilt.onClick {
            hiltBean.say()
        }
    }
}