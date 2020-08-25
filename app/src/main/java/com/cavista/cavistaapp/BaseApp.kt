package com.cavista.cavistaapp

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import androidx.room.Room
import com.cavista.cavistaapp.database.AppDatabase
import com.cavista.cavistaapp.webservice.ApiConstant
import com.cavista.cavistaapp.webservice.ApiService


class BaseApp : Application(), LifecycleObserver {
    var apiService: ApiService? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        daoInstance = AppDatabase.getInstance(this)
        apiService = ApiService()


    }

    override fun onTerminate() {
        super.onTerminate()
        if (instance != null) {
            instance = null
        }
    }

    companion object {
        var instance: BaseApp? = null
        var daoInstance: AppDatabase? = null
    }

}
