package com.example.myapplication.generics

import java.util.Arrays

class GenericsList<T> {

    var instant:Array<Any> = arrayOf()

    fun get(index:Int):T{
        return instant[index] as T
    }

    fun set(index: Int,value: T){
        instant[index] = value as Any
    }

    fun add(value:T){
        instant = arrayOf(instant.copyOf(instant.size + 1))
        instant[instant.size-1] = value as Any
    }

//    fun remove(value: T):Boolean{
//        return instant.remove(value)
//    }
}