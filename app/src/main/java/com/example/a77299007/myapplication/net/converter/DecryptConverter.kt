package com.wazing.myapplication.converter

import com.example.a77299007.myapplication.base.baseBean.EncodeResult
import com.example.a77299007.myapplication.base.baseBean.EncryptUtil
import com.example.a77299007.myapplication.base.baseBean.ResultStatusException
import com.google.gson.Gson
import com.google.gson.JsonIOException

import com.google.gson.TypeAdapter
import okhttp3.ResponseBody
import retrofit2.Converter



data class DecryptConverter<T> constructor(
    private val gson: Gson,
    private val typeAdapter: TypeAdapter<T>
) : Converter<ResponseBody, T> {



    override fun convert(value: ResponseBody): T? {
        var uncheckedJson = value.string()
        val hasEncrypt = uncheckedJson.contains("GzipData")
        if (hasEncrypt) {
            val encodeResult = gson.fromJson(uncheckedJson, EncodeResult::class.java)
            var gzipData: String? = null
            try {
                gzipData = encodeResult.gzipData
            } catch (e: Exception) {
                e.printStackTrace()
                if (e is NullPointerException) {
                    try {
                        throw ResultStatusException(ResultStatusException.DATA_NO_ENCRYPT, "数据未加密异常!")
                    } catch (e1: ResultStatusException) {
                        e1.printStackTrace()
                    }
                }
            }
            var decBase64 = ByteArray(0)
            if (gzipData != null) {
                decBase64 = EncryptUtil.decryptBASE64(gzipData.toByteArray(charset("utf-8")))
            }
            uncheckedJson = EncryptUtil.decryptGZIP(decBase64)
        }

        try {
            if (uncheckedJson != null) {
               // uncheckedJson = "{\"Msg\":\"获取成功\",\"Stat\":1,\"Data\":[[\"日期\",\"用车工地\",\"订车人\",\"预定车型\",\"用车时间\",\"计划作业量\",\"实际派车\",\"跟单人\"],[\"11月08日\",\"碧桂园五期\",\"\",\"63米\",\"16:30\",\"\",\"696\",\"符经理\"],[\"11月13日\",\"惠州南站\",\"\",\"73米\",\"16:00\",\"\",\"888\",\"\"]],\"data\":null,\"rows\":null,\"OpCode\":0,\"ModelState\":null,\"Time\":1547107809,\"total\":0}"
                var DATA_NULL ="\"Data\":[["
                var DATA_NULL2 ="]],\"data\""
               /* if (!TextUtils.isEmpty(uncheckedJson) && uncheckedJson.contains(DATA_NULL) && uncheckedJson.contains(
                        DATA_NULL2
                    )
                ) {
                       uncheckedJson = uncheckedJson.replace(DATA_NULL.toRegex(), "\"Data\":\"[[")
                       uncheckedJson = uncheckedJson.replace(DATA_NULL2.toRegex(), "]]\",\"data\"")

                    //你这里不能直接用TestToNumberBean，因为，以后可能会有新接口，你不能写死。 在？
                    return gson.fromJson(uncheckedJson, object : TypeToken<BaseBean<List<TestToNumberBean>>>() {}.type)


                } else {
                    val result = typeAdapter.fromJson(uncheckedJson) //看看这里能解析成功不
                    return result

                }
*/

                return typeAdapter.fromJson(uncheckedJson)

            } else {
                throw JsonIOException("JSON解析挂了···") as Throwable
            }
        } catch (e: Exception) {
            throw  RuntimeException(e.message)
        }finally {
            value.close()
        }



    }
}