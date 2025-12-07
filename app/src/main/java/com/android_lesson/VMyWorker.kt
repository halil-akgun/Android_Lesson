package com.android_lesson

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class VMyWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {
        val sum = 10 + 20
        Log.d("VMyWorker", "Sum is $sum")
        return Result.success()
    }
}