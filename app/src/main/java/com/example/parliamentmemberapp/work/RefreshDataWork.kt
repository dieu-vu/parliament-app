//NAME: DIEU VU
//DATE CREATED: 10-10-2021

package com.example.parliamentmemberapp.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.parliamentmemberapp.data.MemberDatabase
import com.example.parliamentmemberapp.repository.MemberDataRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters):
        CoroutineWorker(appContext, params) {

        companion object{
                const val WORK_NAME = "RefreshDataWorker"
        }
        override suspend fun doWork(): Result  {
                val database = MemberDatabase.getInstance(applicationContext)
                val repository = MemberDataRepository(database)
                try {
                        repository.refreshDatabase()
                        return Result.success()
                } catch (exception: HttpException){
                        return Result.retry()
                }
        }
}
