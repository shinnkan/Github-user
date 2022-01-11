package com.example.githubuser

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class UserApplication : Application() {

    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        const val username = "shinnkan"
        const val access_key = "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"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }


}