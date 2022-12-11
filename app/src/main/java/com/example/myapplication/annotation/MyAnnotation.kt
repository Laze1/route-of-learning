package com.example.myapplication.annotation

import java.lang.annotation.ElementType


//定义一个注解
annotation class MyAnnotation

//@BindView
class TestAnnotation(){
    @BindView(1)
    val testField:Int = 0

    fun testFunction(){

    }
}

//使用注解
@MyAnnotation
@Deprecated("因为什么原因废弃了")
class AnnotationClass(){

}

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class BindView(
    val id:Int = -1
)