package com.example.myapplication.workmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityWorkManagerBinding
import com.example.myapplication.ext.inflate
import com.example.myapplication.ext.onClick

class WorkManagerActivity : AppCompatActivity() {

    private val binding: ActivityWorkManagerBinding by inflate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)
        testWorkManager()

        binding.btnWorkStart.onClick {
            testWorkManager()
        }
    }

    private fun testWorkManager() {
        val uploadWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<UploadWorker>()
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .build()
        WorkManager.getInstance(this).enqueue(uploadWorkRequest)
    }
}