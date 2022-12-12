package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.generics.GenericsList
import com.example.myapplication.generics.Wrapper


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.img.setImageResource(R.mipmap.ic_test)
    }

}

fun main() {
    val wrapperStr = Wrapper("String")
    val wrapperInt = Wrapper(5)
    println(wrapperStr.instant)
    println(wrapperInt.instant.toString())


    val list = GenericsList<String>()
    list.add("什么")
    val get = list.get(0)
    println(get)
    val list2 = GenericsList<Int>()
    list2.add(124)
    val get2 = list2.get(0)
    println(get2)

}