package com.example.myapplication.jni

class JNILoader {
    companion object {
        init {
            System.loadLibrary("native-clib")
        }
    }

    external fun intFromJNI(): Int

    external fun stringFromJNI(): String

    external fun setJniName(name: String)
}