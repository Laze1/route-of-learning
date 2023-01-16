package com.example.myapplication.test

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 *
 * TestClassTest
 * @author Wu Xi
 * @date 2023/1/16 11:26
 */
class TestClassTest {

    @Test
    fun add() {
        val fx = TestClass().add(2,3)
        assertEquals(5,fx)
    }
}