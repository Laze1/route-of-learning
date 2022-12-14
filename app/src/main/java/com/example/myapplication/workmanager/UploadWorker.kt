package com.example.myapplication.workmanager

import android.app.Notification
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.example.myapplication.R
import kotlinx.coroutines.delay

class UploadWorker(val appContext: Context, workerParams: WorkerParameters):
    CoroutineWorker(appContext, workerParams)  {
    override suspend fun getForegroundInfo(): ForegroundInfo {
        return ForegroundInfo(
            5558, createNotification()
        )
    }

    override suspend fun doWork(): Result {
        // 在这里工作——在本例中，上传图像。
//        startForeground(1, Notification())
        while (true) {
            uploadImages()
        }

        // 使用结果指示工作是否成功完成
        return Result.success()
    }

    private suspend fun uploadImages(){
        delay(1000)
        Log.d("doWork", Thread.currentThread().name)
    }

    private fun createNotification() : Notification {
        return NotificationCompat.Builder(appContext, "CHANNEL_ID")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("textTitle")
            .setContentText("textContent")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .notification
    }
}