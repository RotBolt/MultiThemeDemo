package io.thelimitbreaker.multithemedemo.rest

import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.thelimitbreaker.multithemedemo.rest.model.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.reflect.Type

object RestClient {
    private const val baseUrl = "https://jsonplaceholder.typicode.com/"
    private val httpClient = OkHttpClient()
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun getUsersAsync(): Deferred<Array<UserEntity>> {
        return CoroutineScope(Dispatchers.IO).async {
            val requestUrl = requestUrl("users")
            requestUrl?.let {
                val request = requestUrlBuilder(it)
                handleRequest<Array<UserEntity>>(request, Array<UserEntity>::class.java)
            } ?: throw Exception("request url null")
        }
    }

    private fun requestUrl(method: String): HttpUrl? {
        val urlBuilder = HttpUrl.parse(baseUrl)?.newBuilder()
        return urlBuilder
            ?.addPathSegment(method)
            ?.build()
    }

    private fun requestUrlBuilder(url: HttpUrl): Request {
        return Request.Builder()
            .url(url)
            .build()
    }

    private fun <T> handleRequest(request: Request, type: Type): T {
        val response = httpClient.newCall(request).execute()
        val source = response.body()?.source()
        Log.i("PUI","source ${request.url()}")
        val adapter: JsonAdapter<T> = moshi.adapter(type)
        return source?.let { adapter.fromJson(it) } ?: throw Exception("Response source came null")
    }
}