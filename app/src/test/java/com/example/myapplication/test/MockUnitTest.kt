package com.example.myapplication.test

import android.content.Context
import com.example.myapplication.R
import org.hamcrest.core.Is
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.util.*


@RunWith(MockitoJUnitRunner::class)
class MockUnitTest {

    @Mock
    lateinit var mMockContext: Context


    @Test
    fun readStringFromContext_LocalizedString() {
        //模拟方法调用的返回值，隔离对Android系统的依赖
        Mockito.`when`(mMockContext.getString(R.string.app_name)).thenReturn(FAKE_STRING)
        Assert.assertThat(
            mMockContext.getString(R.string.app_name), Is.`is`(FAKE_STRING)
        )
        Mockito.`when`(mMockContext.packageName).thenReturn("com.jdqm.androidunittest")
        println(mMockContext.packageName)
    }


    companion object {
        private const val FAKE_STRING = "AndroidUnitTest"
    }
}