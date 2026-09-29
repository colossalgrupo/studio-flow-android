package com.colossalgrupo.studioflow

import android.app.Application
import com.colossalgrupo.studioflow.data.AppContainer

class StudioScheduleApplication : Application() {

    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}
