package io.viktoriia.architecture.app

import android.app.Application
import io.viktoriia.architecture.app.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import org.koin.core.logger.Logger

class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val logger: Logger = if (BuildConfig.DEBUG) AndroidLogger() else EmptyLogger()

        startKoin {
            logger(logger)
            androidContext(this@TestApplication)
            modules(koinModules)
        }
    }
}