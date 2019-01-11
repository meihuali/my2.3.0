package com.example.a77299007.myapplication.utils

import com.example.a77299007.myapplication.BuildConfig


object DebugUtils {

    fun isDebug() = BuildConfig.DEBUG || BuildConfig.VERSION_NAME.contains("test")




}