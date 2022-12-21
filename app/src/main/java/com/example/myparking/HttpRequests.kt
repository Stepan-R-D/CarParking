package com.example.myparking

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
//import com.example.simple_english.data.Constants
//import com.example.simple_english.data.HttpMethods
//import kotlinx.serialization.json.*
import okhttp3.*
import okio.IOException
import java.net.URLEncoder
import java.util.concurrent.TimeUnit

enum class HttpMethods {
    GET,
    POST,
    PUT
}

class HttpsRequests {
    private val appBaseUrl = "http://localhost:8081"
    private val client = OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    private fun formRequest(option: String, data: FormBody, method: HttpMethods): Request =
            when (method) {
                HttpMethods.POST -> Request.Builder().url(appBaseUrl + option).post(data).build()
                HttpMethods.PUT -> Request.Builder().url(appBaseUrl + option).put(data).build()
                HttpMethods.GET -> Request.Builder().url(appBaseUrl + option).get().build()
            }

    // Sends request with given params
    fun sendAsyncRequest(
            option: String,
            body: Map<String, String>,
            method: HttpMethods
    ): String {
        val result: String

        val postBody = FormBody.Builder()
        for ((key, value) in body) {
            postBody.add(key, value)
        }
        val postData = postBody.build()

        val request = formRequest(option, postData, method)

        client.newCall(request).execute().use { response ->
            result = when (response.isSuccessful) {
                true -> response.body!!.string()
                false -> ""
            }
        }

        return result
    }
}