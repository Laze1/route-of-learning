package com.example.myapplication.generics


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