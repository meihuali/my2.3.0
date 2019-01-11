package com.wazing.myapplication.converter

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type
import com.google.gson.Gson
import com.google.gson.TypeAdapter

import com.google.gson.reflect.TypeToken




class DecryptConverterFactory : Converter.Factory() {

    private val gson: Gson by lazy(LazyThreadSafetyMode.NONE) {
        Gson()
    }

    companion object {
        fun create(): DecryptConverterFactory {
            return DecryptConverterFactory()
        }
    }



    override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit):
            Converter<ResponseBody, *> {
          val adapter =  gson.getAdapter(TypeToken.get(type))
        return DecryptConverter(gson, adapter as TypeAdapter<Any>)
    }





}