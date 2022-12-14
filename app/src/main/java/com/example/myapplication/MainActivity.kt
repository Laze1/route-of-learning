package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ext.inflate
import com.example.myapplication.ext.onClick
import com.example.myapplication.ext.startActivity
import com.example.myapplication.workmanager.WorkManagerActivity


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by inflate()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setClick()
    }

    private fun setClick() {
        binding.apply {
            btnWork.onClick {
                startActivity(WorkManagerActivity::class.java)
            }
        }
    }

}
