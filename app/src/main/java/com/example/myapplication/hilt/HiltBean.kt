package com.example.myapplication.hilt

import android.util.Log
import javax.inject.Inject

/**
 *
 * HiltBean
 * @author Wu Xi
 * @date 2023/1/16 15:25
 *
 */
class HiltBean @Inject constructor() {

    fun say(){
        Log.d("hilt","I'm ")
    }
}