package com.instructify_me.students.core.presentation

import android.app.Application
import com.instructify_me.students.core.domain.di.AppModule
import com.instructify_me.students.core.domain.di.AppModuleImpl

class InstructifyMeApp: Application() {

    companion object {
        var appModule: AppModule = AppModuleImpl()
    }

    override fun onCreate() {
        super.onCreate()
    }
}