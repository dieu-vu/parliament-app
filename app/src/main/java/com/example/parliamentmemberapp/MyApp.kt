//NAME: DIEU VU
//DATE CREATED: 19-9-2021

package com.example.parliamentmemberapp

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.work.*
import com.example.parliamentmemberapp.work.RefreshDataWorker
import com.example.parliamentmemberapp.work.RefreshDataWorker.Companion.WORK_NAME
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MyApp : Application() {

    //Make sure that the app launch is fast
    private val applicationScope = CoroutineScope(Dispatchers.Default)

    companion object {
        lateinit var appContext: Context
    }


    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        delayedInit()
    }

    private fun delayedInit(){
        applicationScope.launch {
            setupRecurringWork()
        }
    }

    private fun setupRecurringWork(){
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED) //UNMETER: User wont be charged for the network request
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setRequiresDeviceIdle(true) //when the user isn't actively using the device
                }
            }.build()

        //refresh data every six hours with the constraints
        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(
            6,
            TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            RefreshDataWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )
    }
}