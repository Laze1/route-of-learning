package com.example.myapplication.jni

class JniGetInfo {

    companion object{
        @JvmField
        var jniName = ""

        @JvmStatic
        fun setJniName(name: String) {
            jniName = name
        }
    }

}