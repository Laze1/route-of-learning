package com.example.myapplication.observer

/**
 *
 * EventBus
 * @author Wu Xi
 * @date 2022/12/29 15:46
 *
 */
object EventBus {
    private val subscribers = mutableMapOf<Class<*>, MutableList<Subscriber>>()

    fun <T : Any> register(eventType: Class<T>, subscriber: Subscriber) {
        val subscribersForType = subscribers.getOrPut(eventType) { mutableListOf() }
        subscribersForType.add(subscriber)
    }

    fun <T : Any> unregister(eventType: Class<T>, subscriber: Subscriber) {
        val subscribersForType = subscribers[eventType] ?: return
        subscribersForType.remove(subscriber)
    }

    fun post(event: Any) {
        val eventType = event::class.java
        val subscribersForType = subscribers[eventType] ?: return
        for (subscriber in subscribersForType) {
            subscriber.onEvent(event)
        }
    }
}

interface Subscriber {
    fun onEvent(event: Any)
}