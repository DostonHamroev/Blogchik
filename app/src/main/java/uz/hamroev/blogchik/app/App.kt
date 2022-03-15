package uz.hamroev.blogchik.app

import android.app.Application
import uz.hamroev.blogchik.cache.Cache

class App : Application() {
    override fun onCreate() {
        Cache.init(this)
        super.onCreate()
    }
}