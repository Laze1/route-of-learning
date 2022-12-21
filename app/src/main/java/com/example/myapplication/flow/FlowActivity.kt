package com.example.myapplication.flow

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.myapplication.databinding.ActivityFlowBinding
import com.example.myapplication.ext.inflate
import com.example.myapplication.ext.onClick
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FlowActivity : AppCompatActivity() {

    private val binding: ActivityFlowBinding by inflate()
    private val viewModel : FlowViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnSendStart.onClick {
            viewModel.add()
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collect{
                    withContext(Dispatchers.Main) {
                        binding.btnSendStart.text = it.toString()
                    }
                }
            }
        }
    }
}


class FlowViewModel: ViewModel(){

    //读写分离， MutableStateFlow 写， StateFlow读
    private val uiState = MutableStateFlow(0)
    val state : StateFlow<Int> = uiState.asStateFlow()

    fun add(){
        uiState.update {
            it+1
        }
    }
}