package com.example.myapplication.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class UploadWorker(val appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams)  {

    override fun doWork(): Result {
        // 在这里工作——在本例中，上传图像。

        Thread.sleep(3000)
        Log.d("Worker", "doWork: 3000")
        Thread.sleep(3000)
        Log.d("Worker", "doWork: 6000")

        // 使用结果指示工作是否成功完成
        return Result.success()
    }

}