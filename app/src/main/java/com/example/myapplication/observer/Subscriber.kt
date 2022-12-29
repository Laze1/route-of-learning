package com.example.myapplication.observer

/**
 *
 * Subject
 * @author Wu Xi
 * @date 2022/12/29 11:20
 *
 */

interface Subscriber {
    fun onEvent(event: Any)
}

//fun main() {
//    val eventBus = EventBus()
//    val subscriber = object : Subscriber {
//        override fun onEvent(event: Any) {
//            event as String
//            println("Button clicked: $event")
//        }
//    }
//
//    eventBus.register(String::class.java, subscriber)
//    eventBus.post("Button 1")
//    eventBus.post("Button 2")
//    eventBus.unregister(String::class.java, subscriber)
//    eventBus.post("Button 3")
//}
