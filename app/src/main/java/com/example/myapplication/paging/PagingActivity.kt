package com.example.myapplication.paging

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityPagingBinding
import com.example.myapplication.ext.inflate

class PagingActivity : AppCompatActivity() {

    private val binding:ActivityPagingBinding by inflate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.rvPaging.apply {

        }
    }
}