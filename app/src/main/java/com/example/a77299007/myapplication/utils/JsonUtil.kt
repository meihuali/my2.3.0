package com.example.a77299007.myapplication.utils

import com.alibaba.fastjson.JSON

object JsonUtil {

    fun toJson(src: Any): String {
        var result = ""
        try {
            result = JSON.toJSONString(src)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return result
    }


    /**
     * @param json
     * @param clazz serialized JavaBean Class.
     * @param <T>
     * @return
    </T> */
    fun <T> fromJson(json: String, clazz: Class<T>): T? {
        try {
            return JSON.parseObject(json, clazz)
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
    }
}