package com.example.a77299007.myapplication.utils

import android.content.Context
import android.text.TextUtils

object SPUtil {

    private val CURRENT_SP_FILE_NAME = "sp_cache.xml"

    fun <T> saveData(ctx: Context, key: String, value: T) {
        if (TextUtils.isEmpty(key)) {
            return
        }
        val sp = ctx.getSharedPreferences(CURRENT_SP_FILE_NAME, Context.MODE_PRIVATE)
        val editor = sp.edit()
        when (value) {
            is Int -> {
                editor.putInt(key, value)
            }
            is Long -> {
                editor.putLong(key, value)
            }
            is String -> {
                editor.putString(key, value)
            }
            is Boolean -> {
                editor.putBoolean(key, value)
            }
            is Float -> {
                editor.putFloat(key, value)
            }
            else -> {
                editor.putString(key, JsonUtil.toJson(value!!))
            }
        }
        editor.apply()
    }
    fun <T> saveDatas(ctx: Context, key: String, value: T) {
        if (TextUtils.isEmpty(key)) {
            return
        }
        val sp = ctx.getSharedPreferences(CURRENT_SP_FILE_NAME, Context.MODE_PRIVATE)
        val editor = sp.edit()
        when (value) {
            is Int -> {
                editor.putInt(key, value)
            }
            is Long -> {
                editor.putLong(key, value)
            }
            is String -> {
                editor.putString(key, value)
            }
            is Boolean -> {
                editor.putBoolean(key, value)
            }
            is Float -> {
                editor.putFloat(key, value)
            }
            else -> {
                editor.putString(key, JsonUtil.toJson(value!!))
            }
        }
        editor.apply()
    }

    /**
     * 从缓存取出数据
     *
     * @param ctx
     * @param key
     * @return
     */
    fun <T> getData(ctx: Context?, key: String, c: Class<T>): T? {
        if (TextUtils.isEmpty(key)) {
            return null
        }
        if (ctx == null) {
            return null
        }
        val sp = ctx.getSharedPreferences(CURRENT_SP_FILE_NAME, Context.MODE_PRIVATE)
        when (c) {
            Int::class.java -> {
                return sp.getInt(key, 0) as T
            }
            Long::class.java -> {
                return sp.getLong(key, 0L) as T
            }
            String::class.java -> {
                return sp.getString(key, "") as T
            }
            Boolean::class.java -> {
                return sp.getBoolean(key, false) as T
            }
            Float::class.java -> {
                return sp.getFloat(key, 0f) as T
            }
            else -> {
                val str = sp.getString(key, "")
                return JsonUtil.fromJson(str, c)
            }
        }
    }

    fun dellDataString(ctx: Context, key: String) {
        if (TextUtils.isEmpty(key)) {
            return
        }
        val sp = ctx.getSharedPreferences(CURRENT_SP_FILE_NAME, Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.remove(key)
        editor.apply()
    }

    fun deleteData(ctx: Context){
        val sp = ctx.getSharedPreferences(CURRENT_SP_FILE_NAME, Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.clear()
        editor.apply()
    }

}