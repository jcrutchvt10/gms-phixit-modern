package com.gmsphixit.app.core.errors.general

import android.content.Context
import android.content.Intent
import android.os.Build

object ExceptionHandler {
    fun initialize(context: Context, crashActivityClass: Class<*>) {
        val defaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            try {
                val intent = Intent(context, crashActivityClass).apply {
                    putExtra("exception", getStackTrace(throwable))
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                }
                context.startActivity(intent)
            } catch (_: Exception) {
            }
            defaultHandler?.uncaughtException(thread, throwable)
        }
    }

    private fun getStackTrace(throwable: Throwable): String {
        val sw = java.io.StringWriter()
        val pw = java.io.PrintWriter(sw)
        throwable.printStackTrace(pw)
        return sw.toString()
    }
}
